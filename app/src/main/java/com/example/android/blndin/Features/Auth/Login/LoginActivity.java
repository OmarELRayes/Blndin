package com.example.android.blndin.Features.Auth.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blndin.Features.Auth.AuthPresenter;
import com.example.android.blndin.Features.Auth.Login.Presenter.LoginPresenter;
import com.example.android.blndin.Features.Auth.Login.View.LoginView;
import com.example.android.blndin.Features.Auth.SignUp.SignUpActivity;
import com.example.android.blndin.ParentActivity;
import com.example.android.blndin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.login_goto_signup)
    TextView gotoSignUp;
    @BindView(R.id.login_submit)
    Button submit;
    @BindView(R.id.login_et_username)
    EditText et_username;
    @BindView(R.id.login_et_password)
    EditText et_password;
    @BindView(R.id.login_facebook)
    ImageView facebook;
    @BindView(R.id.login_twitter)
    ImageView twitter;
    AuthPresenter presenter;


    @Override
    protected void onStart() {
        super.onStart();
        //currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this);
        presenter.onCreate(this);

        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username = et_username.getText().toString();
//                String password = et_password.getText().toString();
//                presenter.regularLogin(username, password);
                /*Intent intent = new Intent(LoginActivity.this, ParentActivity.class);
                startActivity(intent);*/
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                ((LoginPresenter) presenter).regularLogin(username, password);
                /*Intent intent = new Intent(LoginActivity.this, ParentActivity.class);
                startActivity(intent);*/
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fbLogin(LoginActivity.this);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.twLogin(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LoginPresenterImp) presenter).onResume(this);
    }

    @Override
    public void loginSuccessful(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, ParentActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void success(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, ParentActivity.class));
    }

    @Override
    public void failure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
