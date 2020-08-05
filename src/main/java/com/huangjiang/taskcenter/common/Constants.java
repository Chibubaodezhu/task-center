package com.huangjiang.taskcenter.common;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.Collection;
import java.util.Collections;

public class Constants {

    public static final Collection<ConfigAttribute> NOT_FOUND = Collections.unmodifiableList(SecurityConfig.createList("NOT_FOUND"));
    public static final Collection<ConfigAttribute> PERMISSION_ALL = Collections.unmodifiableList(SecurityConfig.createList("PERMISSION_ALL"));
    public static final ConfigAttribute PERMISSION_ALL_CONFIG_ATTRIBUTE = new SecurityConfig("PERMISSION_ALL");

    public static final String USER_ID = "userId";

    public static final String USER_STATUS_NORMAL = "1"; // 正常
    public static final String USER_STATUS_FROZEN = "2"; // 冻结
    public static final String USER_STATUS_LOCKED = "3"; // 锁定
    public static final String USER_STATUS_QUIT = "4"; // 离职
}
