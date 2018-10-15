package com.niluogege.example.commonsdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.niluogege.example.commonsdk.base.mvp.IPresenter;
import com.niluogege.example.commonsdk.base.mvp.IView;

/**
 * Created by niluogege on 2018/8/22.
 * 针对MVP结构的基类
 */
public abstract class MvpBaseActivity<V extends IView, P extends IPresenter<V>> extends BaseActivity {

    protected P mPresenter = null;//如果当前页面逻辑简单, Presenter 可以为 null

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
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
        if (mPresenter != null) mPresenter.dettach();
        mPresenter = null;
    }


    /**
     * 初始化presenter
     */
    protected abstract P initPresenter();

}
