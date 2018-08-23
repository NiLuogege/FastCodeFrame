package com.niluogege.example.commonsdk.base.mvp;

/**
 * Created by niluogege on 2018/8/23.
 * 每个 Model 都需要实现此类,以满足规范
 */
public interface IModel {
    /**
     * {@link BasePresenter#onDestory()} 时会默认调用
     */
    void onDestroy();
}
