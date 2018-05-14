package com.example.android.blndin.Features.SquadProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.blndin.Adapters.ChatAdapter;
import com.example.android.blndin.Features.SquadProfile.Presenter.SquadProfileChatPresenter;
import com.example.android.blndin.Features.SquadProfile.View.SquadChatView;
import com.example.android.blndin.Models.ChatMessageModel;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ChatSquadActivity extends AppCompatActivity implements SquadChatView {
    LinearLayout writing_layout;
    LinearLayout standing_layout;
    EditText writing_et,standing_et;
    String temp;
    TextView send;
    ListView messageview;
    ChatAdapter adapter;
    ArrayList<ChatMessageModel> models;
    String squad_id, token;
    SquadProfileChatPresenter presenter;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_squad);
        squad_id = getIntent().getExtras().getString("squad_id");
        init();
        set_adapter();

    }
    void init(){
        messageview=(ListView)findViewById(R.id.messages_view);
        send=(TextView)findViewById(R.id.tv_squad_chat_send);
        writing_layout=(LinearLayout)findViewById(R.id.squadchat_writing_layout);
        standing_layout=(LinearLayout)findViewById(R.id.squadchat_standing_layout);
        writing_et=(EditText)findViewById(R.id.et_writing_squad);
        standing_et=(EditText)findViewById(R.id.et_standing_squad);
        standing_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0)
                {
                    temp=s.toString();
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
                if(s.length()==0)
                {
                    temp="";
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
                presenter.addChatMessage(token, squad_id, message);
                adapter.addChatMessage(new ChatMessageModel(message));
                writing_et.setText("");
                writing_layout.setVisibility(GONE);
                standing_layout.setVisibility(VISIBLE);
            }
        });
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.squad_chat_refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMessagesByPage(token, squad_id);
            }
        });
    }
    void set_adapter(){
        token = SharedPreferencesHelper.retrieveDataFromSharedPref(this, "token");
        models = new ArrayList<>();
        adapter = new ChatAdapter(models, this);
        messageview.setAdapter(adapter);
        presenter = new SquadProfileChatPresenterImp(this, this, adapter, models);
        presenter.getMessagesByPage(token, squad_id);
        messageview.setSelection(models.size() - 1);
    }

    public void on_info_click(View view) {
        Intent i = new Intent(ChatSquadActivity.this, DetailsSquadActivty.class);
        i.putExtra("squad_id", squad_id);
        this.startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void setFocus() {
        messageview.setSelection(messageview.getAdapter().getCount() - 1);
    }

    @Override
    public void success(String message) {

    }

    @Override
    public void failure(String message) {

    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }
}
