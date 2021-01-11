package com.atguigu.springcloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 测试了解SpringMVC流程
 *
 * @author ding
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户")
@Slf4j
public class UserController {

    @ApiOperation("获取")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @GetMapping("/user")
    public String getUserName() {
        return "获取leo";
    }

    @ApiOperation("保存测试")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @PostMapping("/user")
    public String postUserName() {
        return "保存leo";
    }

    @ApiOperation("删除")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @DeleteMapping("/user")
    public String deleteUserName() {
        return "删除leo";
    }

    @ApiOperation("更新")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 400, message = "{code:****,message:'失败'}")})
    @PutMapping("/user")
    public String putUserName() {
        return "更新leo";
    }
}
