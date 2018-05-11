package com.example.android.blndin.Features.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.blndin.Features.Login.Presenter.LoginPresenter;
import com.example.android.blndin.Features.Login.View.LoginView;
import com.example.android.blndin.Features.SignUp.SignUpActivity;
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
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this);

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
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                presenter.regularLogin(username, password);
                /*Intent intent = new Intent(LoginActivity.this, ParentActivity.class);
                startActivity(intent);*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    public void loginSuccessful(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginClick() {

    }


}
