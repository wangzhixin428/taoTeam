package com.school.controller;

import com.common.uaa.entity.UserEntity;
import com.school.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    TestService testService;

    /**
     * 老师权限或学生权限
     */
    @GetMapping("/grade")
    @PreAuthorize("hasAnyAuthority('teacher','student', 'test')")
    public Object rs(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("张三",100);
        // 这里的使用本地Java API的方式调用远程的Restful接口
        String echo = testService.echo();
        System.out.println(echo);
        return map;
    }

}
