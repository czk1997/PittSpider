package io.amoe.pittspid.classes.controller;


import com.alibaba.fastjson.JSONObject;
import io.amoe.pittspid.classes.service.impl.ClassesServiceImpl;
import io.amoe.pittspid.course.controller.CoursesController;
import io.amoe.pittspid.course.entity.Courses;
import io.amoe.pittspid.section.controller.SectionsController;
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
    @Autowired
    SectionsServiceImpl sectionsService;

    @GetMapping("update")
    public JSONObject updateDataBase(String subject) {
        ArrayList<Courses> courses = getClassList(subject);
        courses.forEach(item -> classesService.insert(item));

        return new JSONObject();
    }

    @GetMapping("updateSubject")
    public void updateaSectionsForSubject(int term, String subject) {
        ArrayList<Courses> courses = getClassList(subject);
        courses.forEach(item -> classesService.insert(item));
        for (Courses course : courses) {
            CoursesController.getSections(term, course.getCourseNum(), "PIT/PGH").forEach(item -> sectionsService.insert(item));
        }

    }

    @GetMapping("updateAllSubject")
    public void updateAllSubject() {
        String SUBJECTS[] = {"ADMJ", "ADMPS", "AFRCNA", "AFROTC", "ANTH", "ARABIC", "ARTSC", "ASL", "ASTRON", "ATHLTR", "BACC", "BCHS",
                "BECN", "BFAE", "BFIN", "BHRM", "BIND", "BIOENG", "BIOETH", "BIOINF", "BIOSC", "BIOST", "BMIS", "BMKT",
                "BOAH", "BORG", "BQOM", "BSEO", "BSPP", "BUS", "BUSACC", "BUSADM", "BUSBIS", "BUSECN", "BUSENV", "BUSERV",
                "BUSFIN", "BUSHRM", "BUSMKT", "BUSORG", "BUSQOM", "BUSSCM", "BUSSPP", "CDACCT", "CDENT", "CEE", "CGS",
                "CHE", "CHEM", "CHIN", "CLASS", "CLRES", "CLST", "CMME", "CMMUSIC", "CMPBIO", "CMPINF", "COE", "COEA",
                "COEE", "COMMRC", "CS", "CSD", "DENHYG", "DENT", "DIASCI", "DMED", "DSANE", "DUPOSC", "EAS", "ECE", "ECON",
                "EDUC", "EM", "ENDOD", "ENGCMP", "ENGFLM", "ENGLIT", "ENGR", "ENGSCI", "ENGWRT", "ENRES", "EOH", "EPIDEM",
                "FACDEV", "FILMG", "FILMST", "FP", "FR", "FTADMA", "FTDA", "FTDB", "FTDC", "FTDJ", "FTDR", "GEOL", "GER",
                "GERON", "GREEK", "GREEKM", "GSWS", "HAA", "HEBREW", "HIM", "HINDI", "HIST", "HONORS", "HPA", "HPM", "HPS",
                "HRS", "HUGEN", "IDM", "IE", "IL", "IMB", "INFSCI", "INTBP", "IRISH", "ISB", "ISSP", "ITAL", "JPNSE", "JS",
                "KOREAN", "LATIN", "LAW", "LCTL", "LDRSHP", "LEGLST", "LING", "LIS", "LSAP", "MATH", "ME", "MED", "MEDEDU",
                "MEMS", "MILS", "MOLBPH", "MSBMS", "MSCBIO", "MSCBMP", "MSCMP", "MSE", "MSMBPH", "MSMGDB", "MSMI", "MSMPHL",
                "MSMVM", "MSNBIO", "MUSIC", "NEURO", "NPHS", "NROSCI", "NUR", "NURCNS", "NURNM", "NURNP", "NURSAN", "NURSP",
                "NUTR", "ODO", "ORBIOL", "ORSUR", "OT", "PAS", "PEDC", "PEDENT", "PEDS", "PERIO", "PERS", "PETE", "PHARM",
                "PHIL", "PHYS", "PIA", "POLISH", "PORT", "PROSTH", "PS", "PSY", "PSYC", "PSYED", "PT", "PUBHLT", "PUBSRV",
                "PWEA", "QUECH", "REHSCI", "REL", "RELGST", "RESTD", "RUSS", "SA", "SERCRO", "SLAV", "SLOVAK", "SOC",
                "SOCWRK", "SPAN", "STAT", "SWAHIL", "SWBEH", "SWCED", "SWCOSA", "SWE", "SWGEN", "SWINT", "SWRES", "SWWEL",
                "TELCOM", "THEA", "TURKSH", "UKRAIN", "URBNST", "VIET"};
        for (String s : SUBJECTS) {
            updateaSectionsForSubject(2194, s);
        }
    }

    @GetMapping("quickList")
    public List quickList(String subject) {
        return classesService.getBySubject(subject);
    }

    @GetMapping("list")
    public ArrayList<Courses> getClassList(String subject) {
        logger.info("Get Class List for " + subject);
        subject = subject.toUpperCase();
        final String subjectt = subject;
        String url = "https://psmobile.pitt.edu/app/catalog/listCoursesBySubject/UPITT/" + subject.substring(0, 1) + "/" + subject;
        ArrayList<Courses> courses = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Pattern p = Pattern.compile("https://psmobile.pitt.edu/app/catalog/showCourse/UPITT/");
            Elements elements = doc.getElementsByAttributeValueMatching("href", p);
            ArrayList<Integer> coursNumFilter = new ArrayList<>();
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
