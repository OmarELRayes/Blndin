package com.example.android.blndin.Features.Newsfeed;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.blndin.CommentsActivity;
import com.example.android.blndin.Features.HangoutProfile.Model.NormalPostResponse;
import com.example.android.blndin.Models.NewsfeedModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 4/16/2018.
 */

public class NewsfeedAdapter  extends RecyclerView.Adapter<NewsfeedAdapter.ViewHolder>{

    ArrayList<NewsfeedModel> models;
    Context context;
    boolean foreigner = true;

    public NewsfeedAdapter(ArrayList<NewsfeedModel> newsfeedItemsList, Context context) {
        this.models = newsfeedItemsList;
        this.context = context;
    }

    public NewsfeedAdapter(ArrayList<NewsfeedModel> newsfeedItemsList, Context context, boolean foreigner) {
        this.models = newsfeedItemsList;
        this.context = context;
        this.foreigner = foreigner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        if (!foreigner) {
            holder.hangoutSpan.setVisibility(View.GONE);
        }
        if(models.get(position).getIsLiked().equals("0"))
            holder.like_iv.setImageResource(R.drawable.like);
        else  holder.like_iv.setImageResource(R.drawable.fill_like);

        holder.like_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(models.get(holder.getAdapterPosition()).getIsLiked().equals("0"))
                {
                    setLike(SharedPreferencesHelper.retrieveDataFromSharedPref(context,"token"),models.get(holder.getAdapterPosition()).getId());
                    holder.like_iv.setImageResource(R.drawable.fill_like);
                    models.get(position).setIsLiked("1");
                }
                else
                    {
                    setUnlike(SharedPreferencesHelper.retrieveDataFromSharedPref(context,"token"),models.get(holder.getAdapterPosition()).getId());
                    holder.like_iv.setImageResource(R.drawable.like);
                        models.get(position).setIsLiked("0");
                }
            }
        });

        holder.comments_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CommentsActivity.class);
                intent.putExtra("post_id",models.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
        holder.name.setText(models.get(position).getName());
        holder.location.setText(models.get(position).getHangout_address());
        get_image(holder.avatar,models.get(position).getUser_image());
        get_image(holder.newsfeed_cover,models.get(position).getImage());
        holder.content.setText(models.get(position).getContent());
        holder.likes.setText(models.get(position).getLikes());
        holder.comments.setText(models.get(position).getComments());
    }

    void get_image(CircleImageView image,String url) {
        Glide.with(context)
                .load(url)
                .into(image);
    }

    void get_image(ImageView image,String url) {
        Glide.with(context)
                .load(url)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.like_iv) ImageView like_iv;
        @BindView(R.id.comments_iv) ImageView comments_iv;
        @BindView(R.id.item_newsfeed_username) TextView name;
        @BindView(R.id.item_newsfeed_location) TextView location;
        @BindView(R.id.item_newsfeed_hangout_span) LinearLayout hangoutSpan;
        @BindView(R.id.newsfeed_cover)ImageView newsfeed_cover;
        @BindView(R.id.item_hangout_avatar)CircleImageView avatar;
        @BindView(R.id.content_post)TextView content;
        @BindView(R.id.likes_count)TextView likes;
        @BindView(R.id.comments_count)TextView comments;
        @BindView(R.id.hangout_iv) ImageView hangout_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    void setLike(String token,String post_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NormalPostResponse> call = apiInterface.likeHangoutPost(token,post_id);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        Toast.makeText(context,"Liked",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                // hangoutPostsView.failureResponsePosts("Server Error");
            }
        });
    }

    void setUnlike(String token,String post_id) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NormalPostResponse> call = apiInterface.unlikeHangoutPost(token,post_id);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        Toast.makeText(context,"UnLiked",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                // hangoutPostsView.failureResponsePosts("Server Error");
            }
        });
    }
}
