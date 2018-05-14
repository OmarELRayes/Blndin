package com.example.android.blndin.Features.Profile;

import android.content.Context;

import com.example.android.blndin.Features.Profile.Model.ProfileDetailsResponse;
import com.example.android.blndin.Features.Profile.Presenter.ProfileDetailsPresenter;
import com.example.android.blndin.Features.Profile.View.ProfileDetailsView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 5/14/2018.
 */

 public class ProfileDetailsPresenterImp implements ProfileDetailsPresenter {
        ProfileDetailsView view;
        Context context;

    public ProfileDetailsPresenterImp(ProfileDetailsView view, Context context) {
        this.view = view;
        this.context = context;
    }



    @Override
    public void getProfileData(String token) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileDetailsResponse> call=apiInterface.getProfileDetials(SharedPreferencesHelper.retrieveDataFromSharedPref(context,"token"));
        call.enqueue(new Callback<ProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<ProfileDetailsResponse> call, Response<ProfileDetailsResponse> response) {
                if(response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    view.successfullResponse(response.body(),"Success");
                else view.failureResponse("Server Error "+response.body().getStatus() );
            }

            @Override
            public void onFailure(Call<ProfileDetailsResponse> call, Throwable t) {
                view.failureResponse("Server Error");
            }
        });
    }

}
