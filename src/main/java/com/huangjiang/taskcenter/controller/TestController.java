package com.huangjiang.taskcenter.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/set_session")
    public String setSession(@RequestParam("userId") String userId, HttpServletRequest request){
        request.getSession().setAttribute("userId",userId);
        return "session设置成功，设置登录用户为："+userId;
    }
}
