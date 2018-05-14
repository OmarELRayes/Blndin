package com.example.android.blndin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Features.MySquads.Model.MySquadsModel;
import com.example.android.blndin.Features.SquadProfile.ChatSquadActivity;
import com.example.android.blndin.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Luffy on 4/19/2018.
 */

public class MySquadsAdapter extends RecyclerView.Adapter<MySquadsAdapter.ViewHolder> {

    Context context;
    ArrayList<MySquadsModel> models;

    public MySquadsAdapter(Context context, ArrayList<MySquadsModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_squads,parent,false);
        return new MySquadsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MySquadsModel model = models.get(position);
        final String squad_id = model.getId();
        Glide.with(context)
                .load(model.getImage()).error(R.drawable.kappa2)
                .into(holder.image);
        holder.name.setText(model.getTitle());
        holder.membersCount.setText(model.getMembers());
        holder.item_squad_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatSquadActivity.class);
                intent.putExtra("squad_id", squad_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_squad_layout;
        TextView name;
        TextView membersCount;
        CircleImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            item_squad_layout=(LinearLayout)itemView.findViewById(R.id.item_squad_layout);
            name = (TextView) itemView.findViewById(R.id.squadName);
            membersCount = (TextView) itemView.findViewById(R.id.squadMembersCount);
            image = (CircleImageView) itemView.findViewById(R.id.squadImage);
        }
    }
}
