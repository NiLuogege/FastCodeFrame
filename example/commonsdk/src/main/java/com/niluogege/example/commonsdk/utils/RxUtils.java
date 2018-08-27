package com.niluogege.example.commonsdk.utils;

import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.network.ProgressHelper;
import com.niluogege.example.commonsdk.network.RetryWithDelay;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by niluogege on 2018/8/24.
 */

public class RxUtils {

    public static <T> ObservableTransformer<T, T> simpleFlow(BaseActivity activity) {
        return upstream -> upstream
                .compose(activity.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ProgressHelper.applyProgressBar(activity));
    }
}
