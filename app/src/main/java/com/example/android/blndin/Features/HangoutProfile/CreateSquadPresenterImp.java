package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileDetailsResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.NormalPostResponse;
import com.example.android.blndin.Features.HangoutProfile.Presenter.CreateSquadPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.CreateSquadView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 5/13/2018.
 */

public class CreateSquadPresenterImp implements CreateSquadPresenter {
    Context context;
    CreateSquadView view;

    public CreateSquadPresenterImp(Context context, CreateSquadView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void createSquad(String token, String hangout_id,String title,String desc,String message,String image) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       // String token = SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token");
        Call<NormalPostResponse> call = apiInterface.createSquad(token, hangout_id,title,desc,message,image);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                //view.loginSuccessMessage(response.body().getStatus().toString());
                if(response.body()!=null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE)) {
                        view.successfulResponse("Squad Created");
                    } else
                        view.failureResponse("Server Error "+response.body().getStatus());
                }
               else  view.failureResponse("Server Error");
            }

            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }
}
