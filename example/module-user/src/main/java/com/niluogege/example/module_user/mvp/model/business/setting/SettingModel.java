package com.niluogege.example.module_user.mvp.model.business.setting;

import com.niluogege.example.commonsdk.network.DefaultObserver;
import com.niluogege.example.commonsdk.network.RetryWithDelay;
import com.niluogege.example.commonsdk.utils.RxUtils;
import com.niluogege.example.module_user.RestfulApi;
import com.niluogege.example.module_user.mvp.contract.setting.SettingContract;
import com.niluogege.example.module_user.mvp.model.entity.setting.AppSettingInfo;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by niluogege on 2018/8/27.
 */

public class SettingModel implements SettingContract.Model {


    public void loadSetting(SettingContract.View view) {
        RestfulApi.getSettingApiService()
                .getAppSetting2()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<AppSettingInfo>() {
                    @Override
                    protected void onsuccess(AppSettingInfo appSettingInfo) {
                        if (view != null)
                            view.onLoadSuccess(appSettingInfo);
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        if (view != null)
                            view.onLoadFail(throwable);
                    }
                });
    }
}
