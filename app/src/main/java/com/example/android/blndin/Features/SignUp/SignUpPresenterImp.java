package com.example.android.blndin.Features.SignUp;

import com.example.android.blndin.Features.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.SignUp.Presenter.SignUpPresenter;
import com.example.android.blndin.Features.SignUp.View.SignUpView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class SignUpPresenterImp implements SignUpPresenter {

    SignUpView view;

    public SignUpPresenterImp(SignUpView view) {
        this.view = view;
    }

    //TODO
    @Override
    public LatLng getUserLocation() {
        return new LatLng(31.046141, 31.365187);
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
                    view.successfulSignUp(response.body().getStatus().toString());
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    view.failedSignUp();
                }
            });
        }
    }

    @Override
    public void fbSignUp() {

    }

    @Override
    public void twSignUp() {

    }

    boolean validate(String name, String email, String password, String cPassword) {
        // TODO : Validate inputs
        if (password.equals(cPassword)) {
            return true;
        }
        return false;
    }
}
