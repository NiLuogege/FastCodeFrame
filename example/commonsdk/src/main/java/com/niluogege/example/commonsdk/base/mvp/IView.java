package com.niluogege.example.commonsdk.base.mvp;

/**
 *
 * Created by niluogege on 2018/8/23.
 *
 * 每个 View 都需要实现此类, 以满足规范
 */

public interface IView {
    /**
     * 显示加载
     */
    default void showLoading(){

    }

    /**
     * 隐藏加载
     */
    default void hideLoading(){

    }

    /**
     * 销毁
     */
    default void onDestroy(){

    }
}
