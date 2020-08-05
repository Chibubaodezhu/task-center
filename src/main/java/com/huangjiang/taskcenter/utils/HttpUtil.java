package com.huangjiang.taskcenter.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static void parseResponseBody(String jsonString, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try(PrintWriter out = response.getWriter()) {
            out.append(jsonString);
        } catch (IOException e) {
            logger.error("parseResponseBody发生io异常", e);
        }
    }

    public static JSONObject getJsonObjectFromRequestBody(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            logger.error("getJsonObjectFromRequestBody发生io异常", e);
        }
        return JSONObject.parseObject(sb.toString());
    }
}
