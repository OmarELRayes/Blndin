package com.example.android.blndin.Features.HangoutProfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.blndin.Features.HangoutProfile.View.CreateSquadView;
import com.example.android.blndin.R;
import com.example.android.blndin.Util.Constants;
import com.example.android.blndin.Util.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateSquadActivity extends AppCompatActivity implements CreateSquadView{
    @BindView(R.id.title_create_squad)EditText title;
    @BindView(R.id.desc_create_squad)EditText desc;
    @BindView(R.id.message_create_squad)EditText message;
    @BindView(R.id.create_squad_btn)Button create_btn;
    CreateSquadPresenterImp presenter;
    String hangout_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_squad);
        ButterKnife.bind(this);
        hangout_id=getIntent().getStringExtra("hangout_id");
        presenter=new CreateSquadPresenterImp(getApplicationContext(),this);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate().equals("ok"))
                {
                    presenter.createSquad(SharedPreferencesHelper.retrieveDataFromSharedPref(getApplicationContext(),"token"),hangout_id,title.getText().toString(),desc.getText().toString()
                            ,message.getText().toString(),"http://blndincore.s3.eu-west-2.amazonaws.com/defaults/user.jpg");
                    finish();
                }
                else Toast.makeText(getApplicationContext(),validate(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void successfulResponse(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failureResponse(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    String validate(){
        if(title.getText().toString().trim().isEmpty())
            return "Enter Title";
        if(desc.getText().toString().trim().isEmpty())
            return "Enter Description";
        if(message.getText().toString().trim().isEmpty())
            return "Enter Cover Letter";
        return "ok";
    }
}
