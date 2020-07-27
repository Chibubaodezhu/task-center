package com.huangjiang.taskcenter.config.handler;

import com.google.common.collect.Lists;
import com.gtja.pb.combs.common.constants.CommonConstants;
import com.gtja.pb.combs.common.model.Result;
import com.gtja.pb.combs.common.model.UserSession;
import com.gtja.pb.combs.gateway.common.Constants;
import com.gtja.pb.combs.gateway.service.LoginService;
import com.gtja.pb.combs.gateway.service.UserService;
import com.gtja.pb.combs.gateway.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 身份认证成功处理类
 * @author WongCU
 * @date 2018/6/6
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    /**
     * 认证成功处理
     * 1.将用户信息放入session
     * 2.返回给前台登录成功的信息
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        LdapUserDetailsImpl userDetails = (LdapUserDetailsImpl) authentication.getPrincipal();
//        String userId = userDetails.getUsername();
        String userId = (String)request.getAttribute("username");
        UserSession userSession = userService.queryUserSessionById(userId);
        String title = userSession.getTitle();
        if(!StringUtils.isEmpty(title)){
            userSession.setTitles(Lists.newArrayList(title.split(",")));
        }
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        if (!StringUtils.isEmpty(requestURI) && requestURI.contains(Constants.INNER_LOGIN)) {
            // 从matrix登录时，session有效时长为12小时
            session.setMaxInactiveInterval(12 * 60 * 60);
        }
        session.setAttribute(CommonConstants.USER,userSession);
        session.setAttribute(CommonConstants.USER_ID,userId);
        //清空登录缓存
        loginService.delete(userId);
        //返回登陆信息
        Result result = Result.genSuccessResult(userSession);
        HttpUtil.parseResponseBody(result.toString(), response);
    }
}
