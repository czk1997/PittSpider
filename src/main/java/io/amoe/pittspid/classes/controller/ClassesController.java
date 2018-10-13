package io.amoe.pittspid.classes.controller;


import com.alibaba.fastjson.JSONObject;
import io.amoe.pittspid.classes.service.impl.ClassesServiceImpl;
import io.amoe.pittspid.course.entity.Courses;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@RestController
@RequestMapping("/classes")
public class ClassesController {
    Logger logger = LoggerFactory.getLogger(ClassesController.class);
    @Autowired
    ClassesServiceImpl classesService;
    @GetMapping("update")
    public JSONObject updateDataBase(String subject){
        ArrayList<Courses> courses=getClassList(subject);
        courses.forEach(item->classesService.insert(item));

        return new JSONObject();
    }
    @GetMapping("quickList")
    public List quickList(String subject){
        return classesService.getBySubject(subject);
    }
    @GetMapping("list")
    public ArrayList<Courses> getClassList(String subject) {
        logger.info("Get Class List for "+subject);
        subject = subject.toUpperCase();
        final String subjectt = subject;
        String url = "https://psmobile.pitt.edu/app/catalog/listCoursesBySubject/UPITT/" + subject.substring(0, 1) + "/" + subject;
        System.out.println(url);
        ArrayList<Courses> courses = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Pattern p = Pattern.compile("https://psmobile.pitt.edu/app/catalog/showCourse/UPITT/");
            Elements elements = doc.getElementsByAttributeValueMatching("href", p);
            ArrayList<Integer> coursNumFilter=new ArrayList<>();
            for (Element e : elements) {
                String psmUri = e.attr("href");
                String[] psmUrilParts = psmUri.split("/");
                Courses course = new Courses();
                String[] courseNum = e.child(0).child(1).ownText().split(" ");
                if (!coursNumFilter.contains(Integer.parseInt(psmUrilParts[7]))) {
                    coursNumFilter.add(Integer.parseInt(psmUrilParts[7]));
                    course.setSubject(subject);
                    course.setSubjectNum(Integer.parseInt(courseNum[1].replace("\\D+", "")));
                    course.setTitle(e.child(0).child(2).ownText());
                    course.setPsmUri(psmUri);
                    course.setCourseNum(Integer.parseInt(psmUrilParts[7]));
                    course = setCourseDetail(course, psmUri);
                    course.setProgram(psmUrilParts[8]);
                    course.setUnknownDate(LocalDate.parse(psmUrilParts[9], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    courses.add(course);
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return courses;

    }

    private Courses setCourseDetail(Courses course, String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("section-content clearfix");
            for (Element e : elements
            ) {
                String rowName = e.child(0).child(0).ownText().toLowerCase();
                String rowValue = e.child(1).child(0).ownText().toLowerCase();
                if (rowName.contains("component")) {
                    if (rowValue.contains("lab"))
                        course.setLabRequired(1);
                    else
                        course.setLabRequired(0);
                    if (rowValue.contains("rec"))
                        course.setRecRequired(1);
                    else
                        course.setRecRequired(0);
                    if (rowValue.contains("lec"))
                        course.setLecRequired(1);
                    else
                        course.setLecRequired(1);
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return course;
    }
}
