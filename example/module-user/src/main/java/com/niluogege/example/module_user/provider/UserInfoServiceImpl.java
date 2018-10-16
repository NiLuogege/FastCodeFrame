package com.niluogege.example.module_user.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonservice.user.bean.UserInfo;
import com.example.commonservice.user.service.UserInfoInterface;
import com.niluogege.example.commonsdk.utils.arouter.ARouterServicePath;

/**
 * Created by niluogege on 2018/10/16.
 */

@Route(path = ARouterServicePath.USERINFOSERVICE)
public class UserInfoServiceImpl implements UserInfoInterface {
    private Context context;

    @Override
    public UserInfo getUserInfo() {
        return new UserInfo("fastCodeFrame", "1");
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
