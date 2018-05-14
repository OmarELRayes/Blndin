package com.example.android.blndin.Features.Invites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Features.Invites.Model.InviteHangoutResponse;
import com.example.android.blndin.Features.Invites.Presenter.InvitesHangoutPresenter;
import com.example.android.blndin.Features.Invites.View.InviteHangoutView;
import com.example.android.blndin.Features.Newsfeed.model.NewsfeedResponse;
import com.example.android.blndin.Models.InviteHangoutModel;
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

public class InviteHangoutPresenterImp implements InvitesHangoutPresenter {
    Context context;
    InviteHangoutView view;
    RecyclerView.Adapter adapter;
    ArrayList<InviteHangoutModel> models;
    InviteHangoutResponse.Paginator paginator;
    public InviteHangoutPresenterImp(Context context, InviteHangoutView view, RecyclerView.Adapter adapter, ArrayList<InviteHangoutModel> models) {
        this.context = context;
        this.view = view;
        this.adapter = adapter;
        this.models = models;
    }

    @Override
    public void getInvitesHangoutByPage(String token) {
        if(paginator ==null)
        {
            getInvitesHangout(token,"1");
        }
        else{
            if(checkPages(paginator))
                getInvitesHangout(token,String.valueOf(Integer.valueOf(paginator.getCurrent_page())+1));
        }
    }

    void getInvitesHangout(String token,String page) {
        Log.d("Token",token);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<InviteHangoutResponse> call=apiInterface.getInvitesHangout(token,page);
        call.enqueue(new Callback<InviteHangoutResponse>() {
            @Override
            public void onResponse(Call<InviteHangoutResponse> call, Response<InviteHangoutResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator =response.body().getPaginator();
                    models.addAll(response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                }
                else view.failureResponse("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<InviteHangoutResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }
    @Override
    public boolean checkPages(InviteHangoutResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more posts",Toast.LENGTH_SHORT).show();
        return false;
    }


}
