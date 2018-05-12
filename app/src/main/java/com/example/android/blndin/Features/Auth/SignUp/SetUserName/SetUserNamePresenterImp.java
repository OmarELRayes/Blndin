package com.example.android.blndin.Features.Auth.SignUp.SetUserName;

import android.content.Context;
import android.util.Log;

import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public class SetUserNamePresenterImp implements SetUserNamePresenter {

    SetUserNameView view;

    public SetUserNamePresenterImp(SetUserNameView view) {
        this.view = view;
    }

    @Override
    public void submit(String username, final Context context) {
        if (validate(username)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SetUserNameResponse> call = apiInterface.setUserName(
                    username, SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token")
            );
            call.enqueue(new Callback<SetUserNameResponse>() {
                @Override
                public void onResponse(Call<SetUserNameResponse> call, Response<SetUserNameResponse> response) {
                    if (response.body().getStatus().equals("707")) {
                        view.failed(response.body().getMessage());
                    } else {
                        Log.d("5ara", "onResponse: " + SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token") + response.body().getMessage());
                        view.success();
                    }
                }

                @Override
                public void onFailure(Call<SetUserNameResponse> call, Throwable t) {
                    view.failed(t.getMessage());
                }
            });
        } else
            view.failed("It can't be empty, can't it ?!");
    }

    boolean validate(String username) {
        if (!username.isEmpty())
            return true;
        return false;
    }
}
