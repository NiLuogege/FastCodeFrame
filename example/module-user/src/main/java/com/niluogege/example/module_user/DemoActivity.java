package com.niluogege.example.module_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.base.mvp.IPresenter;
import com.niluogege.example.commonsdk.network.DefaultObserver;
import com.niluogege.example.commonsdk.utils.ARoutePath;
import com.niluogege.example.commonsdk.utils.ILog;
import com.niluogege.example.commonsdk.utils.RxUtils;
import com.niluogege.example.module_user.mvp.model.entity.setting.AppSettingInfo;
import com.orhanobut.logger.Logger;

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

    @Override
    protected IPresenter initPresenter() {
        return null;
    }



    public void click2(View view) {
        RestfulApi.getSettingApiService().getAppSetting2()
                .compose(RxUtils.simpleFlow(DemoActivity.this))
                .subscribe(new DefaultObserver<AppSettingInfo>() {
                    @Override
                    protected void onsuccess(AppSettingInfo appSettingInfo) {
                        ILog.e(appSettingInfo.toString());
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }



    public void click3(View view) {
        RestfulApi.getSettingApiService().getAppSetting2()
                .compose(RxUtils.simpleFlow(DemoActivity.this))
                .subscribe(new DefaultObserver<AppSettingInfo>() {
                    @Override
                    protected void onsuccess(AppSettingInfo appSettingInfo) {
                        ILog.e(appSettingInfo.toString());
                    }

                    @Override
                    protected void onFail(Throwable throwable) {
                        Logger.e("onFail");
                    }
                });
    }





}

