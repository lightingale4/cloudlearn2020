package com.atguigu.springcloud.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ding
 */
@RestController
@Slf4j
@RequestMapping("/hello")
@Api(tags = "security")
public class HelloController {

    @GetMapping("/get")
    public String getHello() {
        return "hello";
    }

}
