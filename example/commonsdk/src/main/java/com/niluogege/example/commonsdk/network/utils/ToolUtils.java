package com.niluogege.example.commonsdk.network.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by LuoChen on 2017/10/28.
 */

public class ToolUtils {

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(utfString.substring(pos));
        return sb.toString();
    }

    /**
     * 權限：
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * 說明：手機的IMEI值。
     * 缺點：由權限(READ_PHONE_STATE)可看出，IMEI值為"電話"的屬性，對於Wifi版本的平板會取得NULL值。
     */
    @SuppressLint("MissingPermission")
    public static String getMac(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {

                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if (wifiInfo != null) {
                    return wifiInfo.getMacAddress();
                }
            }
        } catch (Exception e) {

        }

        return "";
    }

    /**
     * 获取androidId
     * 說明：設備第一次啟動時產生的序號。
     * 缺點：網路上部分文章指出，某些廠牌會有android_id重複的情況產生；當機器回原廠設定時，Android_id將會變更。
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId == null ? "" : androidId;
    }


    /**
     * 序列号
     * 說明：硬體的唯一值。
     * 缺點：該值必須要API Level 9才支援；這個問題看專案類型可大可小，對於某些專案來說甚至不是問題。
     */
    public static String getSerial() {
        return Build.SERIAL == null ? "" : Build.SERIAL;
    }

    /**
     * 權限：
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * 說明：手機的IMEI值。
     * 缺點：由權限(READ_PHONE_STATE)可看出，IMEI值為"電話"的屬性，對於Wifi版本的平板會取得NULL值。
     */
    @SuppressLint("MissingPermission")
    public static String getImei(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                String imei = telephonyManager.getDeviceId();
                return imei;
            }
        } catch (Exception e) {

        }

        return "";
    }
}
