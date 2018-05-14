package com.example.android.blndin.Features.MyHangouts;

import android.util.Log;

import com.example.android.blndin.Features.MyHangouts.Model.MyHangoutsResponse;
import com.example.android.blndin.Features.MyHangouts.Presenter.MyHangoutsPresenter;
import com.example.android.blndin.Features.MyHangouts.View.MyHangoutsView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blndin.Util.Constants.SUCCESS_RESPONSE;

/**
 * Created by LeGenÐ on 5/14/2018.
 */

public class MyHangoutsPresenterImp implements MyHangoutsPresenter {
    MyHangoutsView view;

    public MyHangoutsPresenterImp(MyHangoutsView view) {
        this.view = view;
    }

    @Override
    public void getHangouts(String token) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MyHangoutsResponse> call = apiInterface.getMyHangouts(token);
        call.enqueue(new Callback<MyHangoutsResponse>() {
            @Override
            public void onResponse(Call<MyHangoutsResponse> call, Response<MyHangoutsResponse> response) {
                if (response.body().getStatus().equals(SUCCESS_RESPONSE))
                    view.bindHangouts(response.body().getPayload().getHangouts());
            }

            @Override
            public void onFailure(Call<MyHangoutsResponse> call, Throwable t) {
                Log.d("hangouts", "onFailure: " + t.getMessage());
            }
        });
    }
}
