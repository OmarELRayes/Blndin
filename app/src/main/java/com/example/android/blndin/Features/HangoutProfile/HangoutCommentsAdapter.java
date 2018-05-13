package com.example.android.blndin.Features.HangoutProfile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Models.CommentModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 5/13/2018.
 */

public class HangoutCommentsAdapter extends RecyclerView.Adapter<HangoutCommentsAdapter.ViewHolder> {
    Context context;
    ArrayList<CommentModel> models;

    public HangoutCommentsAdapter(Context context, ArrayList<CommentModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public HangoutCommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment,parent,false);
        return new HangoutCommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HangoutCommentsAdapter.ViewHolder holder, int position) {
      //  Log.d("comment_id",models.get(position).getId());
        get_image(holder.avatar,models.get(position).getImage());
        holder.name.setText(models.get(position).getName());
        holder.created_at.setText(models.get(position).getCreated_at());
        holder.comment.setText(models.get(position).getCommnet());
    }
    void get_image(CircleImageView image,String url) {
        Glide.with(context)
                .load(url)
                .into(image);
    }
    @Override
    public int getItemCount() {
        Log.d("size",String.valueOf(models.size()));
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.avatar_commnet) CircleImageView avatar;
          @BindView(R.id.name_comment) TextView name;
           @BindView(R.id.created_at_comment)TextView created_at;
           @BindView(R.id.content_comment)TextView comment;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
