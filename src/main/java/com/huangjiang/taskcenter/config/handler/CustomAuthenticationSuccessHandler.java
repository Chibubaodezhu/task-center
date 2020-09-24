package com.huangjiang.taskcenter.config.handler;

import com.alibaba.fastjson.JSON;
import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.common.JsonResult;
import com.huangjiang.taskcenter.utils.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userId = request.getParameterMap().get("username")[0];
        HttpSession session = request.getSession();
        session.setAttribute(Constants.USER_ID, userId);
        //返回json数据
        JsonResult result = ResultTool.success();
        result.setData(userId);
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
