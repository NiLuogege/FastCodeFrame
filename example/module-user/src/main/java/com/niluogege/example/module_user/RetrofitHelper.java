package com.niluogege.example.module_user;

import com.niluogege.example.commonsdk.network.RestfulApi;

/**
 * Created by niluogege on 2018/8/23.
 */

public class RetrofitHelper {
    private static IdeaApiService ideaApiService = null;

    public static IdeaApiService getIdeaApiService() {
        if (ideaApiService == null) {
            ideaApiService = RestfulApi.getInstence().getApiService(IdeaApiService.class, "http://gank.io/api/data/");
        }
        return ideaApiService;
    }
}
