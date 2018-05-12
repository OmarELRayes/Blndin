package com.example.android.blndin.Features.Auth.SignUp;

import android.content.Context;
import android.util.Log;

import com.example.android.blndin.Features.Auth.AuthPresenterImp;
import com.example.android.blndin.Features.Auth.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.Auth.SignUp.Presenter.SignUpPresenter;
import com.example.android.blndin.Features.Auth.SignUp.View.SignUpView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.facebook.CallbackManager;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class SignUpPresenterImp extends AuthPresenterImp implements SignUpPresenter {

    SignUpView view;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    TwitterAuthClient twitterAuthClient;
    Context context;

    public SignUpPresenterImp(SignUpView view) {
        super(view);
        this.view = view;
    }


    //TODO
    @Override
    public LatLng getUserLocation() {
        return new LatLng(11.11041, 11.119187);
    }

    @Override
    public void regularSignUp(String name, String email, String password, String cPassword) {
        if (validate(name, email, password, cPassword)) {
            // TODO : Get User Location
            LatLng location = getUserLocation();
            String lat = Double.toString(location.latitude);
            String lng = Double.toString(location.longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SignUpResponse> call = apiInterface.regularSignUp(
                    name, email, lat, lng, password
            );
            call.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                    if (response.body().getStatus().equals("200")) {
                        saveToken(response.body().getToken());
                        view.success(response.body().getMessage());
                        Log.d("token", "onResponse: " + response.body().getToken());
                    } else {
                        view.failure(response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    view.failure(t.getMessage());
                }
            });
        }
    }


    boolean validate(String name, String email, String password, String cPassword) {
        // TODO : Validate inputs
        if (password.equals(cPassword)) {
            return true;
        }
        return false;
    }
}
