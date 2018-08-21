package com.niluogege.example.commonsdk.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.niluogege.example.commonsdk.BuildConfig;

public class BaseApplication extends Application {
    private static Application app = null;


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
        app = this;

        initARouter();
    }


    /**
     * 在模拟环境中程序终止会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static Application getApplication() {
        return app;
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.LOG_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(getApplication()); // 尽可能早，推荐在Application中初始化
    }


}
