package com.example.android.blndin.Features.Invites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Features.HangoutProfile.Model.NormalPostResponse;
import com.example.android.blndin.Models.InviteSquadModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luffy on 4/26/2018.
 */

public class InvitationSquadAdapter extends RecyclerView.Adapter<InvitationSquadAdapter.ViewHolder> {
    Context context;
    ArrayList<InviteSquadModel>models;

    public InvitationSquadAdapter(Context context, ArrayList<InviteSquadModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_squad_invitation, parent, false);
        return new InvitationSquadAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        get_image(holder.avatar,models.get(position).getAdmin_image());
        holder.name.setText(models.get(position).getAdmin_name());
        holder.title.setText(models.get(position).getSquad_title());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptInvite(Constants.TOKEN_1,models.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
            }
        });
        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delineInvite(Constants.TOKEN_1,models.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
            }
        });
    }
    void get_image(CircleImageView image,String url) {
        Glide.with(context)
                .load(url)
                .into(image);
    }
    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.invite_squad_avatar)CircleImageView avatar;
        @BindView(R.id.squad_invitation_name)TextView name;
        @BindView(R.id.squad_invitation_squadName)TextView title;
        @BindView(R.id.accept_squad_btn)FancyButton accept;
        @BindView(R.id.decline_squad_btn)FancyButton decline;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    void acceptInvite(String token, String id, final int pos)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NormalPostResponse> call = apiInterface.acceptHangoutInvite(token,id);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
                        delete_item(pos);
                    }
                }
            }
            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                // hangoutPostsView.failureResponsePosts("Server Error");
            }
        });
    }
    void delineInvite(String token,String id, final int pos)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<NormalPostResponse> call = apiInterface.declineHangoutInvite(token,id);
        call.enqueue(new Callback<NormalPostResponse>() {
            @Override
            public void onResponse(Call<NormalPostResponse> call, Response<NormalPostResponse> response) {
                if (response.body() != null)
                {
                    if (response.body().getStatus().equals(Constants.SUCCESS_RESPONSE))
                    {
                        Toast.makeText(context,"Declined",Toast.LENGTH_SHORT).show();
                        delete_item(pos);
                    }
                }
            }
            @Override
            public void onFailure(Call<NormalPostResponse> call, Throwable t) {
                // hangoutPostsView.failureResponsePosts("Server Error");
            }
        });
    }
    void delete_item(int position){
        models.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, models.size());
    }
}
