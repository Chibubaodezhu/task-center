package com.huangjiang.taskcenter.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtil {

    public static String translateParams(String target, Map<String, String> params) {
        Pattern pattern = Pattern.compile("(\\{[^\\}]*\\})");
        Matcher matcher = pattern.matcher(target);
        while (matcher.find()) {
            target = target.replace(matcher.group(), params.get(matcher.group()));
        }
        return target;
    }
}
