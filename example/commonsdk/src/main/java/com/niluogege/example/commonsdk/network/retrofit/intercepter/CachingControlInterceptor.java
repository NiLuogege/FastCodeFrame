package com.niluogege.example.commonsdk.network.retrofit.intercepter;

import android.text.TextUtils;

import com.niluogege.example.commonsdk.network.HttpBaseContext;
import com.niluogege.example.commonsdk.network.retrofit.exception.NoNetworkException;
import com.niluogege.example.commonsdk.network.utils.LogUtil;
import com.niluogege.example.commonsdk.network.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by niluogege on 2018/1/16.
 */

public class CachingControlInterceptor {
    private static final int TIMEOUT_CONNECT = 0; //0秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取我们设置的缓存时常
            String cacheTime = chain.request().header("cacheTime");
            Response originalResponse = chain.proceed(chain.request());
            LogUtil.e("cacheTime=" + cacheTime);//打印缓存时间

            if (TextUtils.isEmpty(cacheTime)) {//不需要缓存
                cacheTime = TIMEOUT_CONNECT + "";//缓存时间为0
            }

            originalResponse = originalResponse.newBuilder()
                    .removeHeader("Pragma")//好要remove掉其他的缓存头，避免服务端进行一些限制，导致客户端不能进行缓存。
                    .removeHeader("Cache-Control")//好要remove掉其他的缓存头，避免服务端进行一些限制，导致客户端不能进行缓存。
                    .header("Cache-Control", "public, max-age=" + cacheTime)//添加自己的Cache-Control
                    .build();
            return originalResponse;
        }
    };

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetworkUtils.isConnected(HttpBaseContext.getContext())) {
                request = request.newBuilder()
                        .removeHeader("Pragma")//好要remove掉其他的缓存头，避免服务端进行一些限制，导致客户端不能进行缓存。
                        .removeHeader("Cache-Control")//好要remove掉其他的缓存头，避免服务端进行一些限制，导致客户端不能进行缓存。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT) //离线的时候为7天的缓存。
                        .build();
                LogUtil.e("没网走缓存");

            }
            Response response = chain.proceed(request);
            LogUtil.e("response.code():" + response.code());
            if (response != null && response.code() == 504) {//504是没有连接到服务器
                throw new NoNetworkException("网络打了个盹,请检查网络");
            }

            return response;
        }
    };
}
