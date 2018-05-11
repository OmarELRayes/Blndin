package com.example.android.blndin.Features.Login;

import com.example.android.blndin.Features.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Login.Presenter.LoginPresenter;
import com.example.android.blndin.Features.Login.View.LoginView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class LoginPresenterImp implements LoginPresenter {


    LoginView view;


    public LoginPresenterImp(LoginView view) {
        this.view = view;
    }


    // check sharedPreferneces
    @Override
    public void onResume() {

    }

    @Override
    public void regularLogin(String email, String password) {
        if (validate(email, password)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponse> call = apiInterface.regularLogin(
                    email, password
            );
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    //TODO : Save in SharedPreferences
                    view.loginSuccessful(response.body().getStatus().toString());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    view.loginFailed();
                }
            });
        }
    }

    @Override
    public void fbLogin() {

    }

    @Override
    public void twLogin() {

    }

    boolean validate(String email, String password) {
        if (password.length() > 6 && email.contains("@") && email.contains(".")) {
            return true;
        }
        return false;

    }
}
