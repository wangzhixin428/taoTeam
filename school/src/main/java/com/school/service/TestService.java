package com.school.service;

import com.common.uaa.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wang-server", path = "/wang")
public interface TestService {
    @RequestMapping(value = "/getTree", method = RequestMethod.POST)
    String echo();

    @RequestMapping(value = "/selectConverterAndMaterial", method = RequestMethod.POST)
    String selectConverterAndMaterial();
}
