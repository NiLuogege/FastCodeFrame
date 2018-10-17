package com.niluogege.example.commonsdk.base.proxy;

import android.app.Application;
import android.content.Context;

import com.niluogege.example.commonsdk.base.AppLifecycle;
import com.niluogege.example.commonsdk.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niluogege on 2018/10/17.
 */

public class ProxyManager implements AppLifecycle {
    ArrayList<AppLifecycle> lifecycles = new ArrayList<>();

    public ProxyManager(Context context) {
        List<AppConfig> appConfigs = new ManifestParser(context).parse();
        if (ListUtils.isListNotEmpty(appConfigs)) {
            for (AppConfig appConfig : appConfigs) {
                appConfig.injectAppLifecycles(context, lifecycles);
            }
        }
    }

    @Override
    public void attachBaseContext(Context base) {
        if (ListUtils.isListNotEmpty(lifecycles)) {
            for (AppLifecycle lifecycle : lifecycles) {
                lifecycle.attachBaseContext(base);
            }
        }
    }

    @Override
    public void onCreate(Application application) {
        if (ListUtils.isListNotEmpty(lifecycles)) {
            for (AppLifecycle lifecycle : lifecycles) {
                lifecycle.onCreate(application);
            }
        }
    }

    @Override
    public void onTrimMemory(Application application, int level) {
        if (ListUtils.isListNotEmpty(lifecycles)) {
            for (AppLifecycle lifecycle : lifecycles) {
                lifecycle.onTrimMemory(application, level);
            }
        }
    }

    @Override
    public void onTerminate(Application application) {
        if (ListUtils.isListNotEmpty(lifecycles)) {
            for (AppLifecycle lifecycle : lifecycles) {
                lifecycle.onTerminate(application);
            }
        }
    }
}
