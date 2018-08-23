package com.niluogege.example.commonsdk.network;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by niluogege on 2018/8/23.
 *
 * 默认处理请求结果的 Observer
 */

public class DefaultObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
