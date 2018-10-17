package com.example.module_order;

import android.app.Application;
import android.content.Context;

import com.niluogege.example.commonsdk.base.AppLifecycle;
import com.niluogege.example.commonsdk.utils.ILog;

/**
 * Created by niluogege on 2018/10/17.
 */

public class OrderAppLifecycle implements AppLifecycle {

    @Override
    public void attachBaseContext(Context base) {
        ILog.e("attachBaseContext");
    }

    @Override
    public void onCreate(Application application) {
        ILog.e("onCreate");
    }

    @Override
    public void onTrimMemory(Application application, int level) {
        ILog.e("onTrimMemory");
    }

    @Override
    public void onTerminate(Application application) {
        ILog.e("onTerminate");
    }
}
