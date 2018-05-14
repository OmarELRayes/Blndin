package com.example.android.blndin.Features.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.blndin.Features.Newsfeed.model.NewsfeedResponse;
import com.example.android.blndin.Features.Profile.Model.ProfilePostsResponse;
import com.example.android.blndin.Features.Profile.Presenter.ProfilePostsPresenter;
import com.example.android.blndin.Features.Profile.View.ProfilePostsView;
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

public class ProfilePostsPresenterImp implements ProfilePostsPresenter {

    ProfilePostsView view;
    RecyclerView.Adapter adapter;
    ArrayList<NewsfeedModel>models;
    Context context;
    ProfilePostsResponse.Paginator paginator;

    public ProfilePostsPresenterImp(ProfilePostsView view, RecyclerView.Adapter adapter, ArrayList<NewsfeedModel> models, Context context) {
        this.view = view;
        this.adapter = adapter;
        this.models = models;
        this.context = context;
    }

    @Override
    public void getPostsByPage(String token) {
        if(paginator ==null)
        {
            getPosts(token,"1");
        }
        else{
            if(checkPage(paginator))
                getPosts(token,String.valueOf(Integer.valueOf(paginator.getCurrent_page())+1));
        }
    }

    @Override
    public boolean checkPage(ProfilePostsResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more posts",Toast.LENGTH_SHORT).show();
        return false;
    }

    void getPosts(String token,String page){
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ProfilePostsResponse> call=apiInterface.getProfilePosts(token,page);
        call.enqueue(new Callback<ProfilePostsResponse>() {
            @Override
            public void onResponse(Call<ProfilePostsResponse> call, Response<ProfilePostsResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator =response.body().getPaginator();
                    models.addAll(response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                }
                else view.failureResponse("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<ProfilePostsResponse> call, Throwable t) {
                view.failureResponse("Server Error ");
            }
        });
    }

}
