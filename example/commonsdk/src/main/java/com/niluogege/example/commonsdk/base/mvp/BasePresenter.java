package com.niluogege.example.commonsdk.base.mvp;


import java.lang.ref.WeakReference;

/**
 * Created by niluogege on 2018/8/23.
 * <p>
 * 基类 Presenter
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {
    protected WeakReference<V> mViewRef;


    @Override
    public void attach(V view) {
        mViewRef = new WeakReference<>(view);

    }

    @Override
    public void dettach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}
