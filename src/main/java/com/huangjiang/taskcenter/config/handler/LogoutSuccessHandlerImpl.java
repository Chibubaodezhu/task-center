package com.huangjiang.taskcenter.config.handler;

import com.huangjiang.taskcenter.common.Result;
import com.huangjiang.taskcenter.utils.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    /**
     * 返回给前台成功信息
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result<Boolean> result = new Result<>();
        result.setData(true);
        HttpUtil.parseResponseBody(result.toString(),response);
    }
}
