package com.example.android.blndin.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.blndin.Models.ChatMessageModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public class ChatAdapter extends BaseAdapter {

    ArrayList<ChatMessageModel> models;
    Context context;

    public ChatAdapter(ArrayList<ChatMessageModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public void addChatMessage(ChatMessageModel message) {
        this.models.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ChatMessageModel message = models.get(position);
        if (message.getOwned().equals("1")) {
            convertView = messageInflater.inflate(R.layout.item_chat_bubble_me, null);
            holder.messagetv = (TextView) convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messagetv.setText(message.getMessage());
        } else {
            convertView = messageInflater.inflate(R.layout.item_chat_bubble_others, null);
            holder.messagetv = (TextView) convertView.findViewById(R.id.message_body);
            holder.nametv = (TextView) convertView.findViewById(R.id.message_name_others);
            holder.useriv = (CircleImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
            holder.messagetv.setText(message.getMessage());
            holder.nametv.setText(message.getUsername());
            Glide.with(context)
                    .load(message.getImage())
                    .into(holder.useriv);
        }
        return convertView;
    }

    class ViewHolder {
        TextView messagetv;
        TextView nametv;
        CircleImageView useriv;
    }
}
