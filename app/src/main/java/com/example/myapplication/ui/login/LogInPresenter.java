package com.example.myapplication.ui.login;


import com.example.myapplication.network.ServiceInterface;
import com.example.myapplication.network.RetroFitFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter implements LogInActivityContract.LogInPresenter {

    LogInActivityContract.LogInView view;

    public LogInPresenter(LogInActivityContract.LogInView view) {
        this.view = view;
    }

    @Override
    public void getOtp(HashMap argument) {

        ServiceInterface serviceInterface = RetroFitFactory.getInstance().create(ServiceInterface.class);
        Call<Object> result = serviceInterface.getData(argument);
        view.showProgressBar();
        result.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                view.hideProgressBar();
                if (response.isSuccessful() && response.body() != null) {
                    HashMap hashMap = (HashMap) response.body();
                    Boolean status = (Boolean) hashMap.get("status");

                    if (status != null && status == true) {
                        view.onSuccess();
                    } else {
                        view.onFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                view.hideProgressBar();
                view.onFailure();

            }
        });
    }

    @Override
    public void getTokenAndFetchProfiles(HashMap argument) {
        ServiceInterface serviceInterface = RetroFitFactory.getInstance().create(ServiceInterface.class);
        Call<Object> result = serviceInterface.verifyOtp(argument);
        result.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.isSuccessful() && response.body() != null) {
                    HashMap hashMap = (HashMap) response.body();
                    String authToken = (String) hashMap.get("token");
                    fetchProfileList(authToken);
                } else {
                    view.onFailure();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                view.onFailure();

            }
        });
    }

    public void fetchProfileList(String authToken) {
        ServiceInterface serviceInterface = RetroFitFactory.getInstance().create(ServiceInterface.class);
        Call<Object> result = serviceInterface.verifyAuth("Bearer " + authToken);
        view.showProgressBar();
        result.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                view.hideProgressBar();

                if (response.isSuccessful() && response.body() != null) {
                    HashMap hashMap = (HashMap) response.body();
                    view.openHomeActivity(hashMap);
                } else {
                    view.onFailure();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                view.onFailure();
                view.hideProgressBar();
            }
        });
    }
}
