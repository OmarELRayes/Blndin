package com.example.android.blndin.Features.MySquads;

import android.util.Log;

import com.example.android.blndin.Features.MySquads.Model.MySquadsResponse;
import com.example.android.blndin.Features.MySquads.Presenter.MySquadsPresenter;
import com.example.android.blndin.Features.MySquads.View.MySquadsView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blndin.Util.Constants.SUCCESS_RESPONSE;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class MySquadsPresenterImp implements MySquadsPresenter {

    MySquadsView view;

    public MySquadsPresenterImp(MySquadsView view) {
        this.view = view;
    }

    @Override
    public void getMySquads(String token) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MySquadsResponse> call = apiInterface.getMySquads(token);
        call.enqueue(new Callback<MySquadsResponse>() {
            @Override
            public void onResponse(Call<MySquadsResponse> call, Response<MySquadsResponse> response) {
                if (response.body().getStatus().equals(SUCCESS_RESPONSE)) {
                    view.bindMySquads(response.body().getPayload().getSquads());
                } else
                    Log.d("squads", "onResponse: " + response.body().getStatus());
            }

            @Override
            public void onFailure(Call<MySquadsResponse> call, Throwable t) {
                Log.d("squads", "onFailure: " + t.getMessage());
            }
        });

    }
}
