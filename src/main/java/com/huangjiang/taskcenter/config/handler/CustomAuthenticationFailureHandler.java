package com.huangjiang.taskcenter.config.handler;

import com.alibaba.fastjson.JSON;
import com.huangjiang.taskcenter.common.JsonResult;
import com.huangjiang.taskcenter.common.ResultCode;
import com.huangjiang.taskcenter.utils.ResultTool;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        //返回json数据
        JsonResult result = null;
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof BadCredentialsException) {
            //密码错误
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = ResultTool.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            //其他错误
            result = ResultTool.fail(ResultCode.COMMON_FAIL);
        }
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
