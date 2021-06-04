package com.example.myapplication.ui.login;

import java.util.HashMap;

public interface LogInActivityContract {

    interface LogInView {
        void onSuccess();

        void onFailure();

        void openHomeActivity(HashMap hashMap);

        void showProgressBar();

        void hideProgressBar();
    }

    interface LogInPresenter {
        void getOtp(HashMap argument);

        void getTokenAndFetchProfiles(HashMap argument);
    }
}
