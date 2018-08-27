package com.niluogege.example.module_user.bean;

/**
 * Created by niluogege on 2018/8/27.
 */

public class AppSettingInfo {
    private String bfa_host;
    private String api_host;

    public String getBfa_host() {
        return bfa_host;
    }

    public void setBfa_host(String bfa_host) {
        this.bfa_host = bfa_host;
    }

    public String getApi_host() {
        return api_host;
    }

    public void setApi_host(String api_host) {
        this.api_host = api_host;
    }

    public String getPiwik_host() {
        return piwik_host;
    }

    public void setPiwik_host(String piwik_host) {
        this.piwik_host = piwik_host;
    }

    private String piwik_host;

    @Override
    public String toString() {
        return "AppSettingInfo{" +
                "bfa_host='" + bfa_host + '\'' +
                ", api_host='" + api_host + '\'' +
                ", piwik_host='" + piwik_host + '\'' +
                '}';
    }
}
