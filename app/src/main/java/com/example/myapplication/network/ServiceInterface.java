package com.example.myapplication.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceInterface {


    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("phone_number_login")
    Call<Object> getData(@Body HashMap arguments);


    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("verify_otp")
    Call<Object> verifyOtp(@Body HashMap arguments);


    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("test_profile_list")
    Call<Object> verifyAuth(@Header("Authorization") String authorization);


}
