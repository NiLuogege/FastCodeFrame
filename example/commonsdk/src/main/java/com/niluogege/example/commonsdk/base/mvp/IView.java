package com.niluogege.example.commonsdk.base.mvp;

/**
 * Created by niluogege on 2018/8/23.
 * <p>
 * 每个 View 都需要实现此类, 以满足规范
 */

public interface IView {

    /**
     * 显示加载框
     */
    void showLoadingDialog();

    /**
     * 隐藏加载框
     */
    void dismissLoadingDialog();
}
