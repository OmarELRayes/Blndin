package com.example.android.blndin.Features.SquadProfile;

import android.util.Log;

import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileDetailsResponse;
import com.example.android.blndin.Features.SquadProfile.Presenter.SquadProfileDetailsPresenter;
import com.example.android.blndin.Features.SquadProfile.View.SquadDetailsView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.blndin.Util.Constants.SUCCESS_RESPONSE;

/**
 * Created by LeGenÐ on 5/14/2018.
 */

public class SquadProfileDetailsPresenterImp implements SquadProfileDetailsPresenter {

    SquadDetailsView view;

    public SquadProfileDetailsPresenterImp(SquadDetailsView view) {
        this.view = view;
    }

    @Override
    public void getDetails(String token, final String squad_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SquadProfileDetailsResponse> call = apiInterface.getSquadDetails(token, squad_id);
        call.enqueue(new Callback<SquadProfileDetailsResponse>() {
            @Override
            public void onResponse(Call<SquadProfileDetailsResponse> call, Response<SquadProfileDetailsResponse> response) {
                if (response.body().getStatus().equals(SUCCESS_RESPONSE)) {
                    SquadProfileDetailsResponse.Payload.Squad squad = response.body().getPayload().getSquad();
                    view.bindDetails(
                            squad.getImage(),
                            squad.getTitle(),
                            squad.getDescription(),
                            squad.getAdmin(),
                            squad.getCreated_at()
                    );
                    if (squad.getMembers() != null)
                        view.bindMembers(squad.getMembers());
                }
            }

            @Override
            public void onFailure(Call<SquadProfileDetailsResponse> call, Throwable t) {
                Log.d("", "onFailure: " + t.getMessage());
            }
        });
    }

}
