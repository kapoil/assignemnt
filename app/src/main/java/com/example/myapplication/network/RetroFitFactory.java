package com.example.myapplication.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetroFitFactory {

    private static Retrofit retrofitFactory = new Retrofit.Builder().baseUrl(getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();


    public static Retrofit getInstance() {
        return retrofitFactory;
    }


    public static boolean isValidResponse(Response<Object> response) {
        return (response != null && response.body() != null);
    }


    public static String getBaseUrl() {
        return "https://testa2.aisle.co/V1/users/";
    }


}
