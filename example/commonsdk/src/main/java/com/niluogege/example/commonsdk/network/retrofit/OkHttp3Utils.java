package com.niluogege.example.commonsdk.network.retrofit;


import com.alibaba.fastjson.JSONObject;
import com.niluogege.example.commonsdk.network.HttpBaseContext;
import com.niluogege.example.commonsdk.network.retrofit.intercepter.CachingControlInterceptor;
import com.niluogege.example.commonsdk.network.retrofit.intercepter.MaintenanceModeIntercepter;
import com.niluogege.example.commonsdk.network.utils.SignRequestUtils;
import com.niluogege.example.commonsdk.network.utils.ToolUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;


/**
 * 类名称：OkHttp3Utils
 * 创建者：Create by liujc
 * 创建时间：Create on 2016/11/22 15:59
 * 描述：okHttp的配置
 */
public class OkHttp3Utils {
    //读超时长，单位：秒
    public static final int READ_TIME_OUT = 20;
    public static final int WRITE_TIME_OUT = 20;
    //连接时长，单位：秒
    public static final int CONNECT_TIME_OUT = 20;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取OkHttpClient对象
     */
    public static OkHttpClient getOkHttpClient() {
        LogInterceptor logInterceptor = new LogInterceptor();

        MaintenanceModeIntercepter maintenanceModeIntercepter = new MaintenanceModeIntercepter();//维护模式拦截器

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(HttpBaseContext.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(SignRequestUtils.signRequest(chain.request()));
            }
        };
        if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(logInterceptor)//自定义的log拦截器
//                    .addInterceptor(httpLoggingInterceptor)//官方提供的log拦截器
                    .addInterceptor(maintenanceModeIntercepter)//拦截器会顺序调用
//                    .addInterceptor(new NetWorkCheckIntercepter())//网络检查拦截器
                    .addNetworkInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR)//有网络时的拦截器 需要设置为addNetworkInterceptor
                    .addInterceptor(CachingControlInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)//没网络时的拦截器 需要设置为 addInterceptor 因为没网的时候不会走addNetworkInterceptor
                    .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null))
                    .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))//创建链接池，这里和源码保持一直
                    .cache(cache)
                    .build();
        }

        return mOkHttpClient;
    }


    public static class LogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            MediaType contentType = null;
            String bodyString = null;
            String bodyLog = "";
            if (response.body() != null) {
                contentType = response.body().contentType();
                bodyString = response.body().string();
                bodyLog = stringifyResponseBody(bodyString);
            }
            // 请求响应时间
            double time = (t2 - t1) / 1e6d;

            //打印log
            logJson(request, response, bodyLog, time);


            if (response.body() != null) {
                // 打印body后原ResponseBody会被清空，需要重新设置body
                ResponseBody body = ResponseBody.create(contentType, bodyString);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }

        /**
         * 打印Log
         *
         * @param request
         * @param response
         * @param bodyLog
         * @param time
         */
        private void logJson(Request request, Response response, String bodyLog, double time) {
            if (request == null || response == null) {
                return;
            }

            JSONObject json = new JSONObject(true);

            JSONObject requestJson = new JSONObject(true);
            JSONObject responseJson = new JSONObject(true);


            //request-----------------------------------------------------

            requestJson.put("url", request.url() + " in " + time + " ms");
            requestJson.put("method", request.method());


            if (request.headers() != null) {
                JSONObject requestHeaderJson = new JSONObject(true);
                Map<String, List<String>> requsetHeadersMap = request.headers().toMultimap();
                Set<String> requsetKeys = requsetHeadersMap.keySet();
                for (String key : requsetKeys) {
                    List<String> value = requsetHeadersMap.get(key);
                    requestHeaderJson.put(key, value.get(0));
                }
                requestJson.put("headers", requestHeaderJson);
            }


            requestJson.put("body", stringifyRequestBody(request));

            //response-----------------------------------------------------

            responseJson.put("code", response.code());


            if (response.headers() != null) {
                JSONObject reponseHeaderJson = new JSONObject(true);
                Map<String, List<String>> responseHeadersMap = response.headers().toMultimap();
                Set<String> responseKeys = responseHeadersMap.keySet();
                for (String key : responseKeys) {
                    List<String> value = responseHeadersMap.get(key);
                    reponseHeaderJson.put(key, value.get(0));
                }
                responseJson.put("headers", reponseHeaderJson);
            }


            responseJson.put("data", JSONObject.parse(bodyLog));

            json.put("requst", requestJson);
            json.put("response", responseJson);


//            Logger.d(JsonUtil.formatJson(json.toJSONString()));
        }

        private static String stringifyRequestBody(Request request) {
            try {

                Request copy = request.newBuilder().build();
                if (copy != null) {
                    RequestBody body = copy.body();
                    if (body != null) {
                        Buffer buffer = new Buffer();
                        body.writeTo(buffer);
                        return buffer.readUtf8();
                    } else {
                        return "null";
                    }

                } else {
                    return "null";
                }

            } catch (final IOException e) {
                return "did not work";
            }
        }

        public String stringifyResponseBody(String responseBody) {

            return ToolUtils.unicode2String(responseBody);

        }

    }
}
