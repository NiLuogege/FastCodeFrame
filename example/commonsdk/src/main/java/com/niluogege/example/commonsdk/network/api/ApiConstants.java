package com.niluogege.example.commonsdk.network.api;


import android.text.TextUtils;

/**
 * 类名称：ApiConstants
 * 创建者：Create by liujc
 * 创建时间：Create on 2017/8/9
 * 描述：api相关常量
 */
public class ApiConstants {
    public static final int BBF = 1;//正式环境
    public static final int TEST = 2;//测试环境
    public static final int BATE = 3;//bate环境


    private static int environment = BBF;//是否是测试模式  这个值 需要在清单文件中修改

    /**
     * 默认的测试环境url
     */
    public static String BASE_TEST_URL = "http://test8.bbf.airent.test.aiershou.com/app/";

    /**
     * 默认测试环境
     */
    public static String TEST_URL = "http://test%s.bbf.t.xianghuanji.com/app/";

    /**
     * 默认线上环境
     */
    public static String BASE_ONLINE_URL = "https://bbf.xianghuanji.com/app/";

    /**
     * 默认beta 环境
     */
    public static String BASE_BATE_URL = "http://beta.bbf.xhj.aihuishou.com/app/";


    /**
     * 默认的bfa测试环境url
     */
    public static String BFA_TEST_URL = "http://test16.bfa.t.xianghuanji.com/app/";

    /**
     * 默认bfa测试环境
     */
    public static String BAF_BASE_TEST_URL = "http://test%s.bfa.t.xianghuanji.com/app/";

    /**
     * 默认bfa线上环境
     */
    public static String BAF_BASE_ONLINE_URL = "https://bfa.xianghuanji.com/app/";

    /**
     * 默认bfa beta 环境
     */
    public static String BAF_BASE_BATE_URL = "http://bfa.beta.xianghuanji.com/app/";


    /**
     * 默认piwikUrl
     */
    public static String PIWIK_URL = "https://piwik.xianghuanji.com";


    /**
     * 获取baseUrl
     */
    public static final String GET_APP_SETTING = "app/setting";



    public static void setEnvironment(int environment) {
        ApiConstants.environment = environment;
    }

    public static int getEnvironment() {
        return environment;
    }

    private static String getBaseUrl() {
        String url = BASE_TEST_URL;
        switch (environment) {
            case BBF://线上
                url = BASE_ONLINE_URL;
                break;
            case TEST://测试
                url = BASE_TEST_URL;
                break;

            case BATE://bate
                url = BASE_BATE_URL;
                break;
        }

        return url;
    }

    /**
     * 获取bfa baseurl
     *
     * @return
     */
    private static String getBfaBaseUrl() {
        String url = BFA_TEST_URL;
        switch (environment) {
            case BBF://线上
                url = BAF_BASE_ONLINE_URL;
                break;
            case TEST://测试
                url = BFA_TEST_URL;
                break;

            case BATE://bate
                url = BAF_BASE_BATE_URL;
                break;
        }

        return url;
    }


    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.AI_RENT_URL:
                host = getBaseUrl();
                break;
            case HostType.XUSER_URL:
                host = getBaseUrl();
                break;
            case HostType.BFA_URL://bfa
                host = getBfaBaseUrl();
                break;
            default:
                host = getBaseUrl();
                break;
        }
        return host;
    }

    public static void setTestHostNum(int testUrlNum) {
        BASE_TEST_URL = String.format(TEST_URL, testUrlNum);
        BFA_TEST_URL = String.format(BAF_BASE_TEST_URL, testUrlNum);
    }

    public static void setBaseOnlineUrl(String baseOnlineUrl) {
        if (!TextUtils.isEmpty(baseOnlineUrl))
            BASE_ONLINE_URL = baseOnlineUrl + "/app";
    }

    public static void setPiwikHost(String piwikHost) {
        if (!TextUtils.isEmpty(piwikHost))
            PIWIK_URL = piwikHost;
    }

    public static void setBfaHost(String bfaHost) {
        if (!TextUtils.isEmpty(bfaHost))
            BAF_BASE_ONLINE_URL = bfaHost;
    }
}
