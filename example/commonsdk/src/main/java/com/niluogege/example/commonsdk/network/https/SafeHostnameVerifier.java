package com.niluogege.example.commonsdk.network.https;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


/**
 * Created by zhpan on 2018/3/21.
 */

public class SafeHostnameVerifier implements HostnameVerifier {
    private static final String IP = "202.108.22.59";//这里的ip 需要根据自己的修改一下

    @Override
    public boolean verify(String hostname, SSLSession session) {
        if (IP.equals(hostname)) {//校验hostname是否正确，如果正确则建立连接
            return true;
        }
        return false;
    }
}
