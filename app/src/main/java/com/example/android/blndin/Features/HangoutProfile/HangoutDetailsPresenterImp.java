package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileDetailsResponse;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutDetailsPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutDetailsView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class HangoutDetailsPresenterImp implements HangoutDetailsPresenter {

    HangoutDetailsView view;

    public HangoutDetailsPresenterImp(HangoutDetailsView view) {
        this.view = view;
    }

    @Override
    public void getHangoutDetails(String hangoutId, Context context) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        String token = SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token");
        Call<HangoutProfileDetailsResponse> call = apiInterface.getHangoutDetails(
                hangoutId, token
        );
        call.enqueue(new Callback<HangoutProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<HangoutProfileDetailsResponse> call, Response<HangoutProfileDetailsResponse> response) {
                //view.loginSuccessMessage(response.body().getStatus().toString());
                if (response.body().getStatus().equals("200")) {
                    view.updateUi(response.body().getHangout());
                } else
                    view.failure(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<HangoutProfileDetailsResponse> call, Throwable t) {
                view.failure(t.getMessage());
            }
        });
    }
}
