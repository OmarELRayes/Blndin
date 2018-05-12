package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfilePostsResponse;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutPostsPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutPostsView;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.CheckHangoutResponse;
import com.example.android.blndin.Models.PostModel;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 5/12/2018.
 */

public class HangoutPostsPresenterImp implements HangoutPostsPresenter {
    Context context;
    HangoutPostsView hangoutPostsView;
    RecyclerView.Adapter adapter;
    ArrayList<PostModel> postModels;
    HangoutProfilePostsResponse.Paginator paginator;
    public HangoutPostsPresenterImp(Context context, HangoutPostsView hangoutPostsView, RecyclerView.Adapter adapter, ArrayList<PostModel> postModels) {
        this.context = context;
        this.hangoutPostsView = hangoutPostsView;
        this.adapter = adapter;
        this.postModels = postModels;
    }

    @Override
    public void getPostsByPage(String token, String hangout_id) {
        if(paginator==null)
        {
            get_posts(token,hangout_id,"1");
        }
        else{
            if(checkPages(paginator))
                get_posts(token,hangout_id,String.valueOf(Integer.valueOf(paginator.getCurrent_page())+1));
        }


    }

    void get_posts(String token, String hangout_id, String page_id){
        Log.d("page_id",page_id);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<HangoutProfilePostsResponse> call = apiInterface.getHangoutPost(token,hangout_id,page_id);
        call.enqueue(new Callback<HangoutProfilePostsResponse>() {
            @Override
            public void onResponse(Call<HangoutProfilePostsResponse> call, Response<HangoutProfilePostsResponse> response) {
                if (response.body() != null)
                {
                    paginator=response.body().getPaginator();
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        postModels.addAll(response.body().getPayload().getData());
                        adapter.notifyDataSetChanged();
                    }
                    else hangoutPostsView.failureResponsePosts("Something error "+response.body().getStatus());
                }
                else  hangoutPostsView.failureResponsePosts("Server Error");
            }
            @Override
            public void onFailure(Call<HangoutProfilePostsResponse> call, Throwable t) {
                hangoutPostsView.failureResponsePosts("Server Error");
            }
        });
    }

    @Override
    public boolean checkPages(HangoutProfilePostsResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more posts",Toast.LENGTH_SHORT).show();
        return false;
    }

}
