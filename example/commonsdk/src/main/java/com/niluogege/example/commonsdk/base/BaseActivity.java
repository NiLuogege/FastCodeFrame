package com.niluogege.example.commonsdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.niluogege.example.commonsdk.base.mvp.IPresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by niluogege on 2018/8/22.
 */
public class BaseActivity<P extends IPresenter> extends RxAppCompatActivity {

    protected P mPresenter = null;//如果当前页面逻辑简单, Presenter 可以为 null

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*注册ARouter*/
        ARouter.getInstance().inject(this);
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
        if (mPresenter != null) mPresenter.onDestory();
        mPresenter = null;
    }
}
