package com.niluogege.example.commonsdk.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niluogege.example.commonsdk.utils.Preconditions;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niluogege on 2018/8/23.
 * retrofit 工厂类
 */

public class RetrofitFactory {


    public static Retrofit getRetrofit(String baseUrl) {

        Preconditions.checkNotNull(baseUrl, "baseUrl not be null");

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")//设置日期格式
                .serializeNulls()//支持null值（默认过滤掉值为null的字段）
                .serializeSpecialFloatingPointValues()//支持特殊的Float 数值，如 Float.POSITIVE_INFINITY
                .create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)//设置baseUrl
                .addConverterFactory(GsonConverterFactory.create(gson))//设置转换机为gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//设置回掉库为Rxjava2
                .client(OkhttpFactory.getOkHttpClient())
                .build();

        return retrofit;
    }
}
