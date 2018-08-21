package com.niluogege.example.module_user;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.niluogege.example.commonsdk.utils.ARoutePath;

/**
 * Created by niluogege on 2018/8/20.
 */
@Route(path = ARoutePath.USER_DEMO)
public class DemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);
    }
}
