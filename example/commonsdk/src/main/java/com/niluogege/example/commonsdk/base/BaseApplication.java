package com.niluogege.example.commonsdk.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * 在模拟环境中程序终止会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
