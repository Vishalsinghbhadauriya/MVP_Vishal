package com.example.mvp_vishal.networking;

import com.example.mvp_vishal.application.AppResponse;
import com.example.mvp_vishal.login.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkRequest {

    @FormUrlEncoded
    @POST("login")
    Observable<AppResponse<Login>> requestLogin(@Field("mobile") String mobile, @Field("password") String password);

    @GET("logout")
    Observable<AppResponse> requestLogout();
}
