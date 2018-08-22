package com.niluogege.example.commonsdk.network.retrofit.intercepter;


import com.niluogege.example.commonsdk.network.HttpBaseContext;
import com.niluogege.example.commonsdk.network.retrofit.exception.NoNetworkException;
import com.niluogege.example.commonsdk.network.utils.LogUtil;
import com.niluogege.example.commonsdk.network.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LuoChen on 2017/12/2.
 * <p>
 * 检查网络拦截器
 */

public class NetWorkCheckIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        LogUtil.e("NetWorkCheckIntercepter");
        if (NetworkUtils.isAvailable(HttpBaseContext.getContext())) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException("网络打了个盹,请检查网络");
        }

    }
}
