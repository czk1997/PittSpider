package io.amoe.pittspid.section.controller;


import com.alibaba.fastjson.JSONObject;
import io.amoe.pittspid.section.entity.Sections;
import io.amoe.pittspid.section.service.impl.SectionsServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@RestController
@RequestMapping("/section")
public class SectionsController {
    static final Logger logger = LoggerFactory.getLogger(SectionsController.class);
    @GetMapping("/list")
    public Sections getSection(@RequestParam int term,@RequestParam int classNum) {
        return getSectionDetail(term,classNum);
    }

    public static Sections getSectionDetail(int term, int classNum) {
        logger.info("Request the Section Detail for term=" + term + ", classNum=" + classNum);
        String SectionURI = "https://psmobile.pitt.edu/app/catalog/classsection/UPITT/" + term + "/" + classNum;
        Sections section = new Sections();
        try {
            Document document = Jsoup.connect(SectionURI).get();
            logger.info(document.title());
            String[] title = document.title().split(" ");
            section.setSubject(title[0]);
            section.setSubjectNum(Integer.parseInt(title[1]));
            section.setSectionNum(Integer.parseInt(title[3]));
            Elements elements = document.getElementsByClass("section-content clearfix");
            section.setTitle(document.getElementsByClass("primary-head").get(0).ownText());
            for (Element e : elements) {
                String rowName = e.getElementsByClass("pull-left").get(0).getElementsByClass("strong").get(0).ownText().toLowerCase();
                String rowValue="";
                if (rowName.contains("room")) {
                    rowValue = e.getElementsByClass("pull-right").get(0).nextElementSibling().nextElementSibling().child(0).ownText() + "\n";
                } else if (rowName.contains("components")) {
                    rowValue = e.getElementsByClass("pull-right").get(0).select("table").select("tr").select("td").get(0).ownText();
                } else {
                    try{
                        rowValue = e.getElementsByClass("pull-right").get(0).child(0).ownText() + "\n";
                    }catch (IndexOutOfBoundsException e2){
                        System.out.println(e);
                    }

                }
                rowValue = rowValue.replace("\"", "");
                rowValue = rowValue.replace("\n", "");

                if (rowName.contains("session")) {
                    section.setSession(rowValue);
                } else if (rowName.contains("class number")) {
                    section.setClassNum(Integer.parseInt(rowValue));
                } else if (rowName.contains("career")) {
                    section.setCareer(rowValue);
                } else if (rowName.contains("units")) {
                    section.setUnits(Integer.parseInt(rowValue.replaceAll("\\D", "")));
                } else if (rowName.contains("grading")) {
                    section.setGrading(rowValue);
                } else if (rowName.contains("description")) {
                    section.setDescription(rowValue);
                } else if (rowName.contains("enrollment requirements")) {
                    section.setPreRequire(rowValue);
                } else if (rowName.contains("instructor")) {
                    section.setInstructor(rowValue);
                } else if (rowName.contains("components")) {
                    if (rowValue.toLowerCase().contains("lecture")) {
                        section.setLecRequre(1);
                    }
                    if (rowValue.contains("recitation")) {
                        section.setRecRequire(1);
                    }
                    if (rowValue.contains("lab")) {
                        section.setLabRequire(1);
                    }
                } else if (rowName.contains("dates")) {
                    String[] dates = rowValue.split(" - ");
                    LocalDate startDate = LocalDate.parse(dates[0], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    LocalDate endDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    section.setStartDate(startDate);
                    section.setEndDate(endDate);
                }
                else if(rowName.contains("section cap")||rowName.contains("class cap")){
                    section.setClassCap(Integer.parseInt(rowValue));
                }
                else if(rowName.contains("wait list cap")){
                    section.setWaitListCap(Integer.parseInt(rowValue));
                }
                else if(rowName.contains("room")){
                    section.setRoom(rowValue);
                }
                else if (rowName.contains("location")){
                    section.setLocation(rowValue);
                }
                else if (rowName.contains("campus")){
                    section.setCampus(rowValue);
                }
                else if(rowName.contains("meets")){
                    section.setMeets(rowValue);
                }
                else if (rowName.contains(("status"))){
                    section.setStatus(rowValue);
                }
                else if(rowName.contains("class attri")){
                    section.setClassAttri(rowValue);
                }
                section.setTerm(term);
            }


        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
        return section;
    }

}
