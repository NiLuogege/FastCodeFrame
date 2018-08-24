package com.niluogege.example.module_user;


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
    Observable<BaseRespose<List<MeiZi>>> getMezi();


}
