package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Adapters.NewsfeedAdapter;
import com.example.android.blndin.Models.PostModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 5/12/2018.
 */

public class HangoutPostsAdapter extends RecyclerView.Adapter<HangoutPostsAdapter.ViewHolder> {
    ArrayList<PostModel> postModels;
    Context context;

    public HangoutPostsAdapter(ArrayList<PostModel> postModels, Context context) {
        this.postModels = postModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newsfeed,parent,false);
        return new HangoutPostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.d("post_id",postModels.get(position).getId());
        holder.hangoutSpan.setVisibility(View.GONE);
        holder.location.setVisibility(View.GONE);
        holder.name.setText(postModels.get(position).getName());
        if(postModels.get(position).getIsLiked().equals("0"))
            holder.like_iv.setImageResource(R.drawable.like);
        else  holder.like_iv.setImageResource(R.drawable.fill_like);
        holder.likes.setText(postModels.get(position).getLikes());
        holder.comments.setText(postModels.get(position).getComments());
        holder.content.setText(postModels.get(position).getContent());
        get_image(holder.avatar_iv,postModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    void get_image(CircleImageView image,String url) {
        Glide.with(context)
                .load(url)
                .into(image);
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         ImageView like_iv;
         CircleImageView avatar_iv;
         TextView name;
         TextView location;
         TextView likes,comments;
         TextView content;
         LinearLayout hangoutSpan;
        public ViewHolder(View itemView) {
            super(itemView);
            like_iv=(ImageView)itemView.findViewById(R.id.like_iv);
            name = (TextView) itemView.findViewById(R.id.item_newsfeed_username);
            location = (TextView) itemView.findViewById(R.id.item_newsfeed_location);
            hangoutSpan = (LinearLayout) itemView.findViewById(R.id.item_newsfeed_hangout_span);
            likes=(TextView)itemView.findViewById(R.id.likes_count);
            comments=(TextView)itemView.findViewById(R.id.comments_count);
            content=(TextView)itemView.findViewById(R.id.content_post);
            avatar_iv=(CircleImageView) itemView.findViewById(R.id.item_hangout_avatar);
        }
    }
}
