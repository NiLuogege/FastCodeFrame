package com.niluogege.example.module_user.mvp.presenter.setting;

import com.niluogege.example.commonsdk.base.mvp.BasePresenter;
import com.niluogege.example.module_user.mvp.contract.setting.SettingContract;
import com.niluogege.example.module_user.mvp.model.business.setting.SettingModel;

/**
 * Created by niluogege on 2018/8/27.
 */

public class SettingPresenter extends BasePresenter<SettingContract.View> {
    private SettingModel model = new SettingModel();


    public void initData() {
        model.loadSetting(mViewRef.get());
    }
}
