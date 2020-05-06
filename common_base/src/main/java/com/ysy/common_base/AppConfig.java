package com.ysy.common_base;

/**
 * Created by YuShengyang on 2020/1/15
 * Email ：18210490506@163.com
 */
public class AppConfig {
    /**
     * 其他版本
     */
    public static final int OTHER = 0;

    /**
     * 测试版本
     */
    public static final int DEBUG = 1;

    /**
     * 正式版本
     */
    public static final int RELEASE = 2;

    /**
     * 是否RELEASE版本不开启日志
     */
    public static boolean DEBUG_LOG = true;

    /**
     * 现在状态
     */
    public static final int ONLINE_SERVER = DEBUG;

    /**
     * 支付成功广播
     */
    public static final String PaySuccess = "com.shunmai.zryp.PAYSUCCESS";

    /**
     * 支付失败广播
     */
    public static final String PayFailed = "com.shunmai.zryp.PAYFAILED";

    /**
     * 支付失败广播
     */
    public static final String LoginInvalid = "com.shunmai.zryp.LOGININVALID";

    /**
     * 更新个人信息
     */
    public static final String RefreshUserInfo = "com.company.wallpaper.RefreshUserInfo";


    /**
     * 正式服务器地址
     */
    public static final String serviceAddress = "http://47.56.203.144:8081/paper/app/";

    /**
     * 测试服务器地址
     */
    public static final String serviceTestAddress = "http://jinxiangwangluo.cn/paper/app/";

    public static final String ShareUrl="http://jinxiangwangluo.cn/paper/app/downloadApp";






}
