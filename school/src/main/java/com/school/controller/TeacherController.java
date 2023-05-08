package com.school.controller;

import com.common.utils.DBUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    /**
     * 老师权限
     */
    @GetMapping("/math/grade")
    @PreAuthorize("hasAuthority('teacher')")
    public Object rs() {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("张三" + i, 100);
            maps.add(map);
        }
        String sql = "SELECT * FROM CATEGORY";
        String datasourceUrl = "jdbc:mysql://127.0.0.1:3306/test?user=root＆password=password&useUnicode=true&characterEncoding=utf-8&useSSL=true&autoReconnect=true&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
        List<HashMap<String, Object>> refResultList = DBUtil.getInstance(datasourceUrl).getRefResultList(sql);
        System.out.println(refResultList);
        return maps;
    }


}
