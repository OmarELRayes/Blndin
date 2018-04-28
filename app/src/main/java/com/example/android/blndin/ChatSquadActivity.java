package com.example.android.blndin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.blndin.Adapters.SquadProfileChatAdapter;
import com.example.android.blndin.Models.SquadChatModel;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ChatSquadActivity extends AppCompatActivity  {
    LinearLayout writing_layout;
    LinearLayout standing_layout;
    EditText writing_et,standing_et;
    String temp;
    TextView send;
    ListView messageview;
    SquadProfileChatAdapter adapter;
    ArrayList<SquadChatModel> models;
    ImageView info_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_squad);
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
                adapter.add_chat_item(new SquadChatModel(writing_et.getText().toString(),true));
                messageview.setSelection(models.size()-1);
                writing_et.setText("");
                writing_layout.setVisibility(GONE);
                standing_layout.setVisibility(VISIBLE);
            }
        });
    }
    void set_adapter(){
        models=new ArrayList<>();
        models.add(new SquadChatModel("MADAFAKA",false,"Momen"));
        models.add(new SquadChatModel("FAG,ME,?",true));
        models.add(new SquadChatModel("Yes you!",false,"Momen"));
        models.add(new SquadChatModel("STFU",true));
        adapter=new SquadProfileChatAdapter(models,this);
        messageview.setAdapter(adapter);
        messageview.setSelection(models.size()-1);
    }

    public void on_info_click(View view) {
        Intent i = new Intent(ChatSquadActivity.this, DetailsSquadActivty.class);
        this.startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
