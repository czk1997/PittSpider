package io.amoe.pittspid.course.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import io.amoe.pittspid.section.entity.Sections;
//import io.amoe.pittspid.section.entity.Sections;
import io.amoe.pittspid.section.entity.Sections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.amoe.pittspid.section.controller.SectionsController.getSectionDetail;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@RestController
@RequestMapping("/course")
public class CoursesController {
    final private Logger logger = LoggerFactory.getLogger(CoursesController.class);

    @GetMapping("sectionList")
    public ArrayList sectionList(@RequestParam int term, @RequestParam int courseNum, @RequestParam String campusId) {
        return getSections(term, courseNum, campusId);
    }

    public static ArrayList<Sections> getSections(int term, int courseNum, String campusId) {
        ArrayList<Sections> secs = new ArrayList<>();
        String url = "https://psmobile.pitt.edu/app/catalog/listsections/UPITT/" + term + "/" + courseNum + "/" + campusId;
        System.out.println(url);
        try {
            Document document = Jsoup.connect(url).get();
            Pattern p = Pattern.compile("https://psmobile.pitt.edu/app/catalog/classsection/UPITT/");
            Elements elements = document.getElementsByAttributeValueMatching("href", p);

            for (Element e : elements) {
                String sectionURI = e.attr("href");
                System.out.println(sectionURI);
                Pattern pp = Pattern.compile("\\d+");
                Matcher matcher = pp.matcher(sectionURI);
                matcher.find();
                String t = matcher.group();
                matcher.find();
                String n = matcher.group();
                Sections sec = getSectionDetail(Integer.parseInt(t), Integer.parseInt(n));
                secs.add(sec);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return secs;
    }
}
