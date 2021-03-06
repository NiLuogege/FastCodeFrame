package com.niluogege.example.commonsdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.niluogege.example.commonsdk.utils.ActivityManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by niluogege on 2018/10/15.
 * 所有Activity都可用的基类
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutResId = initContentView(savedInstanceState);
        if (layoutResId != 0) {
            setContentView(layoutResId);
            /*绑定ButterKnife*/
            unbinder = ButterKnife.bind(this);
            initView(savedInstanceState);
        } else {
            throw new RuntimeException("layoutResId=0");
        }

        /*注册ARouter*/
        ARouter.getInstance().inject(this);
        /*加入到ActivityManager*/
        ActivityManager.getInstance().addActivity(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*从ActivityManager移除*/
        ActivityManager.getInstance().removeActivity(this);
        /*解绑ButterKnife*/
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 设置contentView ID
     *
     * @param savedInstanceState
     * @return
     */
    protected abstract int initContentView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化View
     */
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
}
