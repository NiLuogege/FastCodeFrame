package com.niluogege.example.commonsdk.network;

import com.niluogege.example.commonsdk.utils.Preconditions;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Created by niluogege on 2018/8/23.
 * retorfit管理类 单例类
 */

public class RestfulApi {

    private Map<String, Retrofit> retrofitMap = new HashMap<>();


    private RestfulApi() {
    }

    public static synchronized RestfulApi getInstence() {
        return InnerHolder.manager;
    }

    private static class InnerHolder {
        private static final RestfulApi manager = new RestfulApi();
    }


    /**
     * 返回apiService
     *
     * @param clazz
     * @param baseUrl
     * @param <T>
     * @return
     */
    public synchronized <T> T getApiService(Class<T> clazz, String baseUrl) {
        Preconditions.checkNotNull(baseUrl, "baseUrl not be null");

        Retrofit retrofit = null;

        if (retrofitMap.containsKey(baseUrl)) {//已经创建过
            retrofit = retrofitMap.get(baseUrl);
        } else {//没有创建过

            retrofit = RetrofitFactory.getRetrofit(baseUrl);
            retrofitMap.put(baseUrl, retrofit);
        }

        return retrofit.create(clazz);
    }

}
