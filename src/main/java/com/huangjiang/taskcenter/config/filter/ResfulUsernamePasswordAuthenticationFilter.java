package com.huangjiang.taskcenter.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.huangjiang.taskcenter.security.CypherManager;
import com.huangjiang.taskcenter.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResfulUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    @Qualifier("rsaCypher")
    CypherManager rsaCypher;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        JSONObject requestBody = HttpUtil.getJsonObjectFromRequestBody(request);
        String password = null == requestBody ? "" : (String) requestBody.get(getPasswordParameter());
        String username = null == requestBody ? "" : (String) requestBody.get(getUsernameParameter());
        try {
            logger.info("密码解密开始");
            password = rsaCypher.decrypt(password);
        } catch (Exception e) {
            logger.error("rsa解密发生异常", e);
        }
        username = username.trim();
        request.setAttribute("username", username);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
        return authentication;
    }
}
