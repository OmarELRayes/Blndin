package com.example.android.blndin.Features.Invites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.blndin.Features.Invites.Model.InviteHangoutResponse;
import com.example.android.blndin.Features.Invites.Model.InviteSquadResponse;
import com.example.android.blndin.Features.Invites.Presenter.InviteSquadPresenter;
import com.example.android.blndin.Features.Invites.View.InviteSquadView;
import com.example.android.blndin.Models.InviteSquadModel;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 5/14/2018.
 */

public class InviteSquadPresenterImp implements InviteSquadPresenter {
    Context context;
    RecyclerView.Adapter adapter;
    ArrayList<InviteSquadModel> models;
    InviteSquadView view;
    InviteSquadResponse.Paginator paginator;

    public InviteSquadPresenterImp(Context context, RecyclerView.Adapter adapter, ArrayList<InviteSquadModel> models, InviteSquadView view) {
        this.context = context;
        this.adapter = adapter;
        this.models = models;
        this.view = view;
    }

    @Override
    public void getInviteSquadByPage(String token) {
        if(paginator ==null)
        {
            getInviteSquad(token,"1");
        }
        else{
            if(checkPages(paginator))
                getInviteSquad(token,String.valueOf(Integer.valueOf(paginator.getCurrent_page())+1));
        }
    }
    void getInviteSquad(String token,String page)
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<InviteSquadResponse> call=apiInterface.getInvitesSquad(token,page);
        call.enqueue(new Callback<InviteSquadResponse>() {
            @Override
            public void onResponse(Call<InviteSquadResponse> call, Response<InviteSquadResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator =response.body().getPaginator();
                    models.addAll(response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                }
                else view.failureResponse("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<InviteSquadResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }
    @Override
    public boolean checkPages( InviteSquadResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more posts",Toast.LENGTH_SHORT).show();
        return false;
    }
}
