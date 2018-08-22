package com.niluogege.example.commonsdk.network;

import android.content.Context;

/**
 * 类名称：HttpBaseContext
 * 创建者：Create by liujc
 * 创建时间：Create on 2017/8/9
 * 描述：TODO
 */
public class HttpBaseContext {
  public static Context mAppContext;
  public static String mChannel;


  public static void init(Context context, String channel) {
    mChannel=channel;
    if (mAppContext == null) {
      mAppContext = context.getApplicationContext();
    } else {
      throw new IllegalStateException("set context duplicate");
    }
  }

  public static Context getContext() {
    if (mAppContext == null) {
      throw new IllegalStateException("forget init?");
    } else {
      return mAppContext;
    }
  }
}
