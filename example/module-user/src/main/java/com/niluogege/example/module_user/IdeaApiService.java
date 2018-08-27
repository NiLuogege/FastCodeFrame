package com.niluogege.example.module_user;


import com.niluogege.example.commonsdk.network.BaseRespose;
import com.niluogege.example.module_user.bean.AppSettingInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by dell on 2017/4/1.
 */

public interface IdeaApiService {

    @Headers("Cache-Control: public, max-age=100")
    @GET("福利/10/1")
    Observable<MeiziBaseRespose<List<MeiZi>>> getMezi();


    /**
     * 获取baseUrl
     */
    @GET("app/setting")
    Observable<BaseRespose<AppSettingInfo>> getAppSetting();


    /**
     * 获取baseUrl
     */
    @GET("app/setting")
    Observable<AppSettingInfo> getAppSetting2();


}
