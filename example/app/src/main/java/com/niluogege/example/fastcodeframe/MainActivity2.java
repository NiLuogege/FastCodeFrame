package com.niluogege.example.fastcodeframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.niluogege.example.commonsdk.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by niluogege on 2018/10/15.
 */

public class MainActivity2 extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int initContentView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main2;
    }

    @OnClick(R.id.btn)
    public void onClick() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
