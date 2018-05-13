package com.example.android.blndin.Features.Auth.Login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.android.blndin.Features.Auth.AuthPresenterImp;
import com.example.android.blndin.Features.Auth.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Auth.Login.Presenter.LoginPresenter;
import com.example.android.blndin.Features.Auth.Login.View.LoginView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class LoginPresenterImp extends AuthPresenterImp implements LoginPresenter {


    LoginView view;

    public LoginPresenterImp(LoginView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void onCreate(Context context) {
        super.onCreate(context);
        String data = SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token");
        Log.d("tokenn", "onResume: " + data);
        if (data != null)
            view.loginSuccessful(data);

    }

    @Override
    public void onResume(Activity activity) {
//        String data = SharedPreferencesHelper.retrieveDataFromSharedPref(activity, "token");
//        Log.d("token", "onResume: " + data);
//        if (data != null)
//            view.loginSuccessful(data);
    }

    @Override
    public void regularLogin(String username, String password) {
        if (validate(username, password)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponse> call = apiInterface.regularLogin(
                    username, password
            );
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.body().getStatus().equals("200")) {
                        saveToken(response.body().getToken());
                        Log.d("Token", "onResponse: " + response.body().getToken());
                        view.loginSuccessful(response.body().getStatus().toString());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    view.failure(t.getMessage());
                }
            });
        }
    }

    boolean validate(String username, String password) {
        if (password.length() >= 6) {
            return true;
        }
        return false;

    }


}
