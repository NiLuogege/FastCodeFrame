package com.example.commonservice.user.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.example.commonservice.user.bean.UserInfo;

/**
 * Created by niluogege on 2018/10/16.
 */

public interface UserInfoInterface extends IProvider {
    UserInfo getUserInfo();
}
