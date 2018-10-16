package com.niluogege.example.fastcodeframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.niluogege.example.commonsdk.base.BaseActivity;
import com.niluogege.example.commonsdk.utils.arouter.ARouteOrderPath;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by niluogege on 2018/10/15.
 */

public class MainActivity2 extends BaseActivity {
    @BindView(R2.id.btn)
    Button btn;

    @Override
    protected int initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main2;
    }

    @OnClick(R2.id.btn)
    public void onClick() {
//        startActivity(new Intent(this, MainActivity.class));
        ARouter.getInstance().build(ARouteOrderPath.ORDER_DEMO).navigation();
    }
}
