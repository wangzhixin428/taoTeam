package com.school.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.common.utils.DBUtil;
import com.school.service.TestService;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/selectConverterAndMaterial")
    @PreAuthorize("hasAnyAuthority('test','student')")
    public String rs() {
        String selectConverterAndMaterial = testService.selectConverterAndMaterial();
        System.out.println(selectConverterAndMaterial);

//        String sql = "SELECT * FROM CATEGORY";
//        String datasourceUrl = "jdbc:mysql://localhost:3306/test?user=root＆password=password&useUnicode=true&characterEncoding=utf-8&useSSL=true&autoReconnect=true&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
//        List<HashMap<String, Object>> refResultList = DBUtil.getInstance(datasourceUrl).getRefResultList(sql);
//        System.out.println(refResultList);
//        String result = (String) JSONArray.toJSON(refResultList);
        return selectConverterAndMaterial;
    }


}
