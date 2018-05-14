package com.example.android.blndin.Features.Newsfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.blndin.Features.Newsfeed.model.NewsfeedResponse;
import com.example.android.blndin.Features.Newsfeed.presenter.NewsfeedPresenter;
import com.example.android.blndin.Features.Newsfeed.view.NewsfeedView;
import com.example.android.blndin.Models.NewsfeedModel;
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

public class NewsfeedPresenterImp implements NewsfeedPresenter {
    Context context;
    RecyclerView.Adapter adapter;
    NewsfeedView view;
    ArrayList<NewsfeedModel>models;
    NewsfeedResponse.Paginator paginator_discover;
    NewsfeedResponse.Paginator paginator_wall;
    public NewsfeedPresenterImp(Context context, RecyclerView.Adapter adapter, NewsfeedView view, ArrayList<NewsfeedModel> models) {
        this.context = context;
        this.adapter = adapter;
        this.view = view;
        this.models = models;
    }

    @Override
    public void getNewsfeedsDiscoverByPage(String token) {
        if(paginator_discover ==null)
        {
            getNewsfeedDiscover(token,"1");
        }
        else{
            if(checkPages(paginator_discover))
                getNewsfeedDiscover(token,String.valueOf(Integer.valueOf(paginator_discover.getCurrent_page())+1));
        }
    }

    void getNewsfeedDiscover(String token,  String page)
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<NewsfeedResponse> call=apiInterface.getDiscoverNewsfeed(token,page);
        call.enqueue(new Callback<NewsfeedResponse>() {
            @Override
            public void onResponse(Call<NewsfeedResponse> call, Response<NewsfeedResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator_discover =response.body().getPaginator();
                    models.addAll(response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                }
                else view.failureResponse("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<NewsfeedResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }

    @Override
    public void getNewsfeedWallByPage(String token) {
        if(paginator_wall ==null)
        {
            getNewsfeedWall(token,"1");
        }
        else{
            if(checkPages(paginator_wall))
                getNewsfeedWall(token,String.valueOf(Integer.valueOf(paginator_wall.getCurrent_page())+1));
        }
    }
    void getNewsfeedWall(String token,String page)
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<NewsfeedResponse> call=apiInterface.getWallNewsfeed(token,page);
        call.enqueue(new Callback<NewsfeedResponse>() {
            @Override
            public void onResponse(Call<NewsfeedResponse> call, Response<NewsfeedResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator_wall =response.body().getPaginator();
                    models.addAll(response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                }
                else view.failureResponse("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<NewsfeedResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }
    @Override
    public boolean checkPages(NewsfeedResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more posts",Toast.LENGTH_SHORT).show();
        return false;
    }
}
