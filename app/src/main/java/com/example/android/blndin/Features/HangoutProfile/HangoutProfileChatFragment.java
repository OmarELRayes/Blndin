package com.example.android.blndin.Features.HangoutProfile;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blndin.Adapters.ChatAdapter;
import com.example.android.blndin.Features.HangoutProfile.Presenter.HangoutProfileChatPresenter;
import com.example.android.blndin.Features.HangoutProfile.View.HangoutChatView;
import com.example.android.blndin.Models.ChatMessageModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HangoutProfileChatFragment extends Fragment implements HangoutChatView {


    LinearLayout writing_layout;
    LinearLayout standing_layout;
    EditText writing_et, standing_et;
    String temp;
    TextView send;
    ListView messageview;
    ChatAdapter adapter;
    ArrayList<ChatMessageModel> models;
    HangoutProfileChatPresenter presenter;
    String hangoutId, token;
    Context context;
    SwipeRefreshLayout refreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hangout_profile_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        context = getContext();
        if (getUserVisibleHint()) {
            set_adapter();
        }
    }

    void init(View view) {
        messageview = (ListView) view.findViewById(R.id.hangout_chat_messages_view);
        send = (TextView) view.findViewById(R.id.hangout_chat_send);
        writing_layout = (LinearLayout) view.findViewById(R.id.hangout_chat_writing_layout);
        standing_layout = (LinearLayout) view.findViewById(R.id.hangout_chat_standing_layout);
        writing_et = (EditText) view.findViewById(R.id.hangout_chat_writing_et);
        standing_et = (EditText) view.findViewById(R.id.hangout_chat_standing_et);
        standing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    temp = s.toString();
                    writing_layout.setVisibility(VISIBLE);
                    standing_layout.setVisibility(GONE);
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
                    writing_layout.setVisibility(GONE);
                    standing_layout.setVisibility(VISIBLE);
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
                String message = writing_et.getText().toString();
                presenter.addChatMessage(token, hangoutId, message);
                adapter.addChatMessage(new ChatMessageModel(message));
                writing_et.setText("");
                writing_layout.setVisibility(GONE);
                standing_layout.setVisibility(VISIBLE);
            }
        });
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.hangout_chat_refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPostsByPage(token, hangoutId);
            }
        });
    }


    void set_adapter() {
        hangoutId = "15";
        token = SharedPreferencesHelper.retrieveDataFromSharedPref(context, "token");
        models = new ArrayList<>();
        adapter = new ChatAdapter(models, getContext());
        messageview.setAdapter(adapter);
        presenter = new HangoutProfileChatPresenterImp(getContext(), this, adapter, models);
        presenter.getPostsByPage(token, hangoutId);
        messageview.setSelection(models.size() - 1);
    }

    @Override
    public void success(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFocus() {
        messageview.setSelection(messageview.getAdapter().getCount() - 1);
    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }
}
