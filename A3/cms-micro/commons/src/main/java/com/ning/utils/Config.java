package com.ning.utils;

public class Config {
    //权限
    public static final String NORMAL_USER = "USER";
    public static final String VIP_USER = "MEMBER";
    public static final String PRIMARY_EDITOR = "EDITOR";
    public static final String SENIOR_EDITOR = "REVIEWER";
    public static final String ADMIN = "ADMIN";
    public static final String PRE_TEXT = "ROLE_";

    //禁言
    public static final int BANNED_CODE = 2;
    public static final int FREE_CODE = 1;

    //新闻状态
    public static final String MODIFY_CODE = "Modifying";
    public static final String PUBLISH_CODE = "Published";
    public static final String REVIEW_CODE = "Reviewing";
    public static final String CANCEL_CODE = "Canceled";

    //审核状态
    public static final String WAIT_CODE = "Wait";
    public static final String DONE_CODE = "Done";

    //返回
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;
    public static final int UNAUTHORIZED_CODE = 401;

    //默认类别
    public static final int CATEGORY_ORIGIN = 1;
}