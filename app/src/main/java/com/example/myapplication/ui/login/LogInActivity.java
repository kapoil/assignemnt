package com.example.myapplication.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ui.homepage.activity.HomePageActivity;
import com.example.myapplication.R;
import com.example.myapplication.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInActivityContract.LogInView {

    //region view variables
    @BindView(R.id.continue_button)
    TextView continueText;
    @BindView(R.id.phone_number_editText)
    EditText phoneNumberEditText;
    @BindView(R.id.otp_editText)
    EditText otpEditText;
    @BindView(R.id.phone_number_prefix)
    TextView phoneNumberprefix;
    @BindView(R.id.edit_icon_imageView)
    ImageView editIconImageView;
    @BindView(R.id.get_otp_textView)
    TextView getOtpTextView;
    @BindView(R.id.phone_textView)
    TextView phoneTextView;
    //endregion

    //region static variable
    public static final String DEFAULT_PHONE_NUMBER = "+919876543212";
    public static final String PHONE_NUMBER = "9876543212";
    public static final String DEFAULT_OTP = "1234";
    public ProgressDialog progressDialog;

    // endregion

    //region helper variable
    boolean isOtpScreen;
    LogInActivityContract.LogInPresenter logInPresenter;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        logInPresenter = new LogInPresenter(this);

        phoneNumberEditText.setText(PHONE_NUMBER);

        progressDialog = new ProgressDialog(this);
        setUpCLickListener();

    }

    // region helper methods
    private void setUpCLickListener() {

        continueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //since button is same . this optScreen varaible helps to maintain which api to hit
                if (isOtpScreen) {

                    if (otpEditText.getText().toString() == null ||
                            otpEditText.getText().toString() == "") {
                        Utils.showToast(getApplicationContext(), getString(R.string.please_fill_otp));
                        return;
                    }

                    HashMap<String, String> hashMap = new HashMap();
                    hashMap.put("number", DEFAULT_PHONE_NUMBER);
                    hashMap.put("otp", otpEditText.getText().toString());
                    logInPresenter.getTokenAndFetchProfiles(hashMap);
                } else {

                    if (phoneNumberEditText.getText().toString() == null ||
                            phoneNumberEditText.getText().toString() == "") {
                        Utils.showToast(getApplicationContext(), getString(R.string.please_fill_phone_number));
                        return;
                    }

                    HashMap<String, String> hashMap = new HashMap();
                    hashMap.put("number", "+91"+ phoneNumberEditText.getText().toString());
                    logInPresenter.getOtp(hashMap);
                }
            }
        });

        editIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // endregion

    // region presenters methods
    @Override
    public void onSuccess() {
        // hide phone number
        phoneNumberEditText.setVisibility(View.GONE);
        phoneNumberprefix.setVisibility(View.GONE);

        //show otp edit text
        otpEditText.setVisibility(View.VISIBLE);
        editIconImageView.setVisibility(View.VISIBLE);

        // update otp screen
        getOtpTextView.setText(DEFAULT_PHONE_NUMBER);
        phoneTextView.setText(R.string.enter_the_otp);
        otpEditText.setText(DEFAULT_OTP);
        isOtpScreen = true;
    }

    @Override
    public void onFailure() {
        Utils.showToast(this, getString(R.string.some_error_occured));
    }

    @Override
    public void openHomeActivity(HashMap hashMap) {

        //setting up bundle
        Bundle extras = new Bundle();
        extras.putSerializable("HashMap", hashMap);

        //opening activity
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtras(extras);
        startActivity(intent);

    }

    @Override
    public void showProgressBar() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...Please wait");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            }
        });
    }

    @Override
    public void hideProgressBar() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //endregion
}