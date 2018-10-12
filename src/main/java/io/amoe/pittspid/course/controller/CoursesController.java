package io.amoe.pittspid.course.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import io.amoe.pittspid.section.entity.Sections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
    @GetMapping("sectionList")
    public JSONObject sectionList() {
        return new JSONObject();
    }
//    public static ArrayList<Sections> updateSection(){
//        return null;
//    }

}
