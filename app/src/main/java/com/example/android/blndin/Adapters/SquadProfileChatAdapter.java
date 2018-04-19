package com.example.android.blndin.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.blndin.Models.SquadChatModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * Created by Luffy on 4/19/2018.
 */

public class SquadProfileChatAdapter extends RecyclerView.Adapter<SquadProfileChatAdapter.ViewHolder> {
    ArrayList<SquadChatModel> models;
    Context context;

    public SquadProfileChatAdapter(ArrayList<SquadChatModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public SquadProfileChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_squad_chat,parent,false);
        return new SquadProfileChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SquadProfileChatAdapter.ViewHolder holder, int position) {
        if(models.get(position).getOwner())
        {
            holder.textView.setBackgroundResource(R.drawable.rounded_grey_text_chat_solid);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.END ;
            holder.textView.setLayoutParams(params);
        }
        holder.textView.setText(models.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv_squad_chat);
        }
    }
}
