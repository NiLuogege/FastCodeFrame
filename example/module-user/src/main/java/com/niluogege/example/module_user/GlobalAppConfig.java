package com.niluogege.example.module_user;

import android.content.Context;

import com.niluogege.example.commonsdk.base.AppLifecycle;
import com.niluogege.example.commonsdk.base.proxy.AppConfig;

import java.util.ArrayList;

/**
 * Created by niluogege on 2018/10/17.
 */

public class GlobalAppConfig implements AppConfig {
    @Override
    public void injectAppLifecycles(Context context, ArrayList<AppLifecycle> lifecycles) {
        lifecycles.add(new UserAppLifecycle());
    }
}
