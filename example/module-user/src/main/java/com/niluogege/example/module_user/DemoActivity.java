package com.niluogege.example.module_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.network.ApiException;
import com.niluogege.example.commonsdk.network.DefaultObserver;
import com.niluogege.example.commonsdk.utils.ARoutePath;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by niluogege on 2018/8/20.
 */
@Route(path = ARoutePath.USER_DEMO)
public class DemoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);


    }

    public void click(View view) {
        RestfulApi.getIdeaApiService().getMezi().compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(respose -> {
                    if (respose != null && respose instanceof BaseRespose) {
                        return Observable.just(respose.getResults());
                    } else {
                        return Observable.error(new ApiException(respose.getCode(), "数据不符合规范"));
                    }
                })
                .subscribe(new DefaultObserver<List<MeiZi>>() {
                    @Override
                    protected void onsuccess(List<MeiZi> meiZis) {

                    }

                    @Override
                    protected void onFail(Throwable throwable) {

                    }
                });
    }
}

//new Observer<List<MeiZi>>() {
//@Override
//public void onSubscribe(Disposable d) {
//
//        }
//
//@Override
//public void onNext(List<MeiZi> meiZis) {
//        Logger.e("onNext");
//        Logger.e(meiZis.toString(), "dll");
//        }
//
//@Override
//public void onError(Throwable e) {
//        Logger.e("onError");
//        }
//
//@Override
//public void onComplete() {
//        Logger.e("onComplete");
//        }
//        }
