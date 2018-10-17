package com.niluogege.example.commonsdk.base.proxy;

import android.content.Context;

import com.niluogege.example.commonsdk.base.AppLifecycle;

import java.util.ArrayList;

/**
 * Created by niluogege on 2018/10/17.
 * 用于在manifest中预先做配置
 */

public interface AppConfig {

    void injectAppLifecycles(Context context, ArrayList<AppLifecycle> lifecycles);

}
