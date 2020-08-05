package com.huangjiang.taskcenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/set_session")
    public String setSession(@RequestParam("userId") String userId, HttpServletRequest request){
        logger.info(userId);
        return "session设置成功，设置登录用户为："+userId;
    }
}
