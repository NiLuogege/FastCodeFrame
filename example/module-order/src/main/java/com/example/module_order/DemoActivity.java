package com.example.module_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commonservice.user.bean.UserInfo;
import com.example.commonservice.user.service.UserInfoInterface;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.utils.DialogUtils;
import com.niluogege.example.commonsdk.utils.arouter.ARouteOrderPath;
import com.niluogege.example.commonsdk.utils.arouter.ARouterServicePath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by niluogege on 2018/8/29.
 */

@Route(path = ARouteOrderPath.ORDER_DEMO)
public class DemoActivity extends BaseActivity {
    @BindView(R2.id.tv_service_text)
    TextView tv_service_text;

    @Override
    protected int initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.order_activity_demo;
    }


    @OnClick({R2.id.btn_type, R2.id.btn_name, R2.id.btn_dialog})
    public void onViewClicked(View view) {

        int i = view.getId();
        if (i == R.id.btn_type) {
            UserInfo userInfo = ARouter.getInstance().navigation(UserInfoInterface.class).getUserInfo();
            tv_service_text.setText(userInfo.toString() + " btn_type");
        } else if (i == R.id.btn_name) {
            UserInfo userInfo1 = ((UserInfoInterface) ARouter.getInstance().build(ARouterServicePath.USERINFOSERVICE).navigation()).getUserInfo();
            tv_service_text.setText(userInfo1.toString() + " btn_name");
        } else if (i == R.id.btn_dialog) {
            DialogUtils.createCommonDialog(this, R.layout.order_dialog_demo, null).show();
        }

    }

//    @OnClick(R2.id.btn_type)
//    void onClick1() {
//        UserInfo userInfo = ARouter.getInstance().navigation(UserInfoInterface.class).getUserInfo();
//        tv_service_text.setText(userInfo.toString() + " btn_type");
//    }
//
//    @OnClick(R2.id.btn_name)
//    void onClick2() {
//        UserInfo userInfo = ((UserInfoInterface) ARouter.getInstance().build(ARouterServicePath.USERINFOSERVICE).navigation()).getUserInfo();
//        tv_service_text.setText(userInfo.toString() + " btn_name");
//    }
//
//    @OnClick(R2.id.btn_dialog)
//    void onClick3() {
//        DialogUtils.createCommonDialog(this, R.layout.order_dialog_demo, null).show();
//    }
}
