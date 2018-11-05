package com.niluogege.example.fastcodeframe;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.utils.ToastUtils;
import com.niluogege.example.commonsdk.utils.arouter.ARouteOrderPath;
import com.niluogege.example.commonsdk.utils.arouter.ARouteUserPath;
import com.niluogege.example.commonsdk.utils.oopinter.FunctionManager;
import com.niluogege.example.fastcodeframe.intertest.InterTest;

import butterknife.OnClick;


/**
 * Created by niluogege on 2018/10/15.
 */

public class MainActivity2 extends BaseActivity {

    private InterTest interTest;

    @Override
    protected int initContentView(@Nullable Bundle savedInstanceState) {
        interTest = new InterTest();
        return R.layout.activity_main2;
    }

    @OnClick(R2.id.btn_user)
    public void onClick() {
        ARouter.getInstance().build(ARouteUserPath.USER_SETTING).navigation();

    }

    @OnClick(R2.id.btn_order)
    public void onClick1() {
        ARouter.getInstance().build(ARouteOrderPath.ORDER_DEMO).navigation();

    }

    @OnClick(R2.id.btn_1)
    public void on1() {
        String s = FunctionManager.getInstance().invokeFunc(InterTest.withParamAndResult, InterTest.withParamAndResult, String.class);
        ToastUtils.show(s);
    }

    @OnClick(R2.id.btn_3)
    public void on3() {
        FunctionManager.getInstance().invokeFunc(InterTest.WithParamOnly, "两个");
    }

    @OnClick(R2.id.btn_2)
    public void on2() {
        interTest.remove();
    }
}
