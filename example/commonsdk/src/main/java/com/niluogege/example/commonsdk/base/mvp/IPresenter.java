package com.niluogege.example.commonsdk.base.mvp;

import android.os.Bundle;

import com.niluogege.example.commonsdk.base.MvpBaseActivity;
import com.niluogege.example.commonsdk.base.BaseLazyFragment;

/**
 * Created by niluogege on 2018/8/23.
 * <p>
 * 每个 Presenter 都需要实现此类,以满足规范
 */

public interface IPresenter<V> {

    /**
     * 在{@link MvpBaseActivity#onCreate(Bundle)}}
     * 或者{@link BaseLazyFragment#onCreate(Bundle)}
     * 中调用
     *
     * @param view
     */
    void attach(V view);

    /**
     * 在{@link MvpBaseActivity#onDestroy()}}
     * 或者{@link BaseLazyFragment#onDestroy()}
     * 中调用
     */
    void dettach();


}
