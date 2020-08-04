package com.huangjiang.taskcenter.config.handler;

import com.alibaba.fastjson.JSON;
import com.huangjiang.taskcenter.common.Constants;
import com.huangjiang.taskcenter.common.JsonResult;
import com.huangjiang.taskcenter.utils.ResultTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.USER_ID);
        JsonResult result = ResultTool.success();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}

