package com.niluogege.example.module_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.network.DefaultObserver;
import com.niluogege.example.commonsdk.network.ProgressHelper;
import com.niluogege.example.commonsdk.network.RetryWithDelay;
import com.niluogege.example.commonsdk.network.exception.ApiException;
import com.niluogege.example.commonsdk.utils.ARoutePath;
import com.niluogege.example.commonsdk.utils.RxUtils;
import com.niluogege.example.module_user.bean.AppSettingInfo;
import com.orhanobut.logger.Logger;

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
        doNetWork();
    }

    private void doNetWork() {
        RestfulApi.getIdeaApiService().getMezi()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ProgressHelper.applyProgressBar(DemoActivity.this))
                .flatMap(respose -> {
                    if (!respose.isError()) {
                        return Observable.just(respose.getResults());
                    } else {
                        return Observable.error(new ApiException(-1000, "meizi 的 message"));
                    }
                })
                .subscribe(new DefaultObserver<List<MeiZi>>() {
                    @Override
                    protected void onsuccess(List<MeiZi> meiZis) {
                        Logger.e("onsuccess");
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }

    private void doNetWork2() {
        RestfulApi.getIdeaApiService().getMezi()
                .compose(RxUtils.simpleFlow(this))
                .flatMap(respose -> {
                    if (!respose.isError()) {
                        return Observable.just(respose.getResults());
                    } else {
                        return Observable.error(new ApiException(-1000, "meizi 的 message"));
                    }
                })
                .subscribe(new DefaultObserver<List<MeiZi>>() {
                    @Override
                    protected void onsuccess(List<MeiZi> meiZis) {
                        Logger.e("onsuccess");
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }


    public void click2(View view) {
        getSetting2();
    }

    private void getSetting() {
        RestfulApi.getSettingApiService().getAppSetting()
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(ProgressHelper.applyProgressBar(DemoActivity.this))
                .flatMap(respose -> {
                    if (respose.success()) {
                        return Observable.just(respose.getData());
                    } else {
                        return Observable.error(new ApiException(respose.getCode(), respose.getMsg()));
                    }
                })
                .subscribe(new DefaultObserver<AppSettingInfo>() {
                    @Override
                    protected void onsuccess(AppSettingInfo appSettingInfo) {
                        Logger.e("onsuccess");
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }

    private void getSetting2() {
        RestfulApi.getSettingApiService().getAppSetting()
                .compose(RxUtils.simpleFlow(DemoActivity.this))
                .flatMap(respose -> {
                    if (respose.success()) {
                        return Observable.just(respose.getData());
                    } else {
                        return Observable.error(new ApiException(respose.getCode(), respose.getMsg()));
                    }
                })
                .subscribe(new DefaultObserver<AppSettingInfo>() {
                    @Override
                    protected void onsuccess(AppSettingInfo appSettingInfo) {
                        Logger.e("onsuccess");
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }


}

