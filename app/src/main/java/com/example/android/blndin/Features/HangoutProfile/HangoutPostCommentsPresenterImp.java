package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.blndin.Features.HangoutProfile.Model.CommentsResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileDetailsResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.NormalPostResponse;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutPostCommentsPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutPostCommentsView;
import com.example.android.blndin.Models.CommentModel;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 5/13/2018.
 */

public class HangoutPostCommentsPresenterImp implements HangoutPostCommentsPresenter {
    Context context;
    RecyclerView.Adapter adapter;
    ArrayList<CommentModel> models;
    CommentsResponse.Paginator paginator;
    HangoutPostCommentsView view;

    public HangoutPostCommentsPresenterImp(Context context, RecyclerView.Adapter adapter, ArrayList<CommentModel> models, HangoutPostCommentsView view) {
        this.context = context;
        this.adapter = adapter;
        this.models = models;
        this.view = view;
    }

    @Override
    public void getCommentsByPage(String token, String post_id) {
        if(paginator==null)
        {
            getComments(token,post_id,"1");
        }
        else{
            if(check_page(paginator))
                getComments(token,post_id,String.valueOf(Integer.valueOf(paginator.getCurrent_page())+1));
        }
    }

    void getComments(String token, String post_id, final String page_id){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CommentsResponse> call = apiInterface.getHangoutPostComments(token, post_id,page_id);
        call.enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                //view.loginSuccessMessage(response.body().getStatus().toString());
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    paginator=response.body().getPaginator();
                    models.addAll(0,response.body().getPayload().getData());
                    adapter.notifyDataSetChanged();
                    if(page_id.equals("1"))
                        view.setFocus(models.size());
                    view.stopRefreshing();
                }
                else    view.failureResponseComments("Server Error "+response.body().getStatus());
            }
            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                view.failureResponseComments("Connection-Error");
            }
        });
    }
    @Override
    public void addComment(String token, String post_id, final String comment) {
        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        Call<NormalPostResponse> call=apiInterface.add_comment_hangout(token,post_id,comment);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                {
                    CommentModel commentModel=new CommentModel("Mostafa Waleed","now","http://blndincore.s3.eu-west-2.amazonaws.com/defaults/user.jpg",comment);
                    models.add(commentModel);
                    adapter.notifyDataSetChanged();
                }
                else
                    view.failureResponseComments("Server Error "+response.body().getStatus());
            }

            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                view.failureResponseComments("Connection-Error");
            }
        });

    }

    @Override
    public boolean check_page(CommentsResponse.Paginator paginator) {
        if(!Integer.valueOf(paginator.getCurrent_page()).equals(Integer.valueOf(paginator.getPages())))
        {
            Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context,"no more comments",Toast.LENGTH_SHORT).show();
        return false;
    }
}
