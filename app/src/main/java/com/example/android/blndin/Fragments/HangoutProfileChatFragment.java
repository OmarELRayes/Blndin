package com.example.android.blndin.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.blndin.Adapters.SquadProfileChatAdapter;
import com.example.android.blndin.Models.SquadChatModel;
import com.example.android.blndin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileChatFragment extends Fragment {


    LinearLayout writing_layout;
    LinearLayout standing_layout;
    EditText writing_et, standing_et;
    String temp;
    TextView send;
    ListView messageview;
    SquadProfileChatAdapter adapter;
    ArrayList<SquadChatModel> models;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangout_profile_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        set_adapter();
    }

    void init(View view) {
        messageview = (ListView) view.findViewById(R.id.messages_view);
        send = (TextView) view.findViewById(R.id.tv_squad_chat_send);
        writing_layout = (LinearLayout) view.findViewById(R.id.squadchat_writing_layout);
        standing_layout = (LinearLayout) view.findViewById(R.id.squadchat_standing_layout);
        writing_et = (EditText) view.findViewById(R.id.et_writing_squad);
        standing_et = (EditText) view.findViewById(R.id.et_standing_squad);
        standing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    temp = s.toString();
                    writing_layout.setVisibility(View.VISIBLE);
                    standing_layout.setVisibility(View.GONE);
                    writing_et.setText(temp);
                    // Selection.setSelection((Editable) writing_et.getText(),writing_et.getSelectionEnd());
                    writing_et.requestFocus();
                    writing_et.setSelection(writing_et.getText().length());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        writing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    temp = "";
                    writing_layout.setVisibility(View.GONE);
                    standing_layout.setVisibility(View.VISIBLE);
                    standing_et.setText(temp);
                    // Selection.setSelection((Editable) standing_et.getText(),standing_et.getSelectionStart());
                    standing_et.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add_chat_item(new SquadChatModel(writing_et.getText().toString(), true));
                messageview.setSelection(models.size() - 1);
            }
        });
    }

    void set_adapter() {
        models = new ArrayList<>();
        models.add(new SquadChatModel("MADAFAKA", false, "Momen"));
        models.add(new SquadChatModel("FAG,ME,?", true));
        models.add(new SquadChatModel("Yes you!", false, "Momen"));
        models.add(new SquadChatModel("STFU", true));
        adapter = new SquadProfileChatAdapter(models, getContext());
        messageview.setAdapter(adapter);
        messageview.setSelection(models.size() - 1);
    }

}
