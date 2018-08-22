package com.niluogege.example.commonsdk.network.retrofit.intercepter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.niluogege.example.commonsdk.network.HttpBaseContext;
import com.niluogege.example.commonsdk.network.basebean.BaseRespose;
import com.niluogege.example.commonsdk.network.utils.LogUtil;
import com.niluogege.example.commonsdk.network.utils.ToolUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by LuoChen on 2017/10/28.
 */

public class MaintenanceModeIntercepter implements Interceptor {
    public static final String ACTION_MAINTENANCE_MODE="action_maintenance_mode";
    public static final int CODE_MAINTENANCE_MODE=60001;

    private Response response;
    MediaType contentType;
    String bodyString;
    private ResponseBody body;

    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtil.e("MaintenanceModeIntercepter");
        Request request = chain.request();

        if (request!=null){
            response = chain.proceed(request);
            if (response !=null){
                body = response.body();
                if (body !=null){
                    contentType = body.contentType();
                     bodyString = ToolUtils.unicode2String(body.string());
                    Gson gson = new Gson();
                    BaseRespose baseRespose = gson.fromJson(bodyString, BaseRespose.class);
                    int code = baseRespose.getCode();
                    if (code==CODE_MAINTENANCE_MODE){//维护模式
                        Intent intent =new Intent();
                        intent.setAction(ACTION_MAINTENANCE_MODE);
                        LocalBroadcastManager.getInstance(HttpBaseContext.getContext()).sendBroadcast(intent);
                    }


                }
            }
        }

        if (body != null) {
            // 打印body后原ResponseBody会被清空，需要重新设置body
            ResponseBody body = ResponseBody.create(contentType, bodyString);
            return response.newBuilder().body(body).build();
        } else {
            return response;
        }
    }
}
