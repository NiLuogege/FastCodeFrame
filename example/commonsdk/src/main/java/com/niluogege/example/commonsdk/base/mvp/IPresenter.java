package com.niluogege.example.commonsdk.base.mvp;

/**
 * Created by niluogege on 2018/8/23.
 *
 * 每个 Presenter 都需要实现此类,以满足规范
 */

public interface IPresenter {
    /**
     * 做初始化操作
     */
    void onStart();

    /**
     * 销毁
     *  {@link android.app.Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestory()}
     */
    void onDestory();
}
