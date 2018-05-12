package com.example.android.blndin.Features.Auth.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.blndin.Features.Auth.AuthPresenter;
import com.example.android.blndin.Features.Auth.SignUp.Presenter.SignUpPresenter;
import com.example.android.blndin.Features.Auth.SignUp.SetUserName.SetUserNameDialogFragment;
import com.example.android.blndin.Features.Auth.SignUp.SetUserName.onSubmitClicked;
import com.example.android.blndin.Features.Auth.SignUp.View.SignUpView;
import com.example.android.blndin.ParentActivity;
import com.example.android.blndin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpView, onSubmitClicked {

    @BindView(R.id.signup_submit)
    Button submit;
    @BindView(R.id.signup_name)
    EditText et_name;
    @BindView(R.id.signup_email)
    EditText et_email;
    @BindView(R.id.signup_password)
    EditText et_password;
    @BindView(R.id.signup_confirm_password)
    EditText et_cpassword;
    @BindView(R.id.signup_facebook)
    ImageView facebook;
    @BindView(R.id.signup_twitter)
    ImageView twitter;

    AuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        presenter = new SignUpPresenterImp(this);
        presenter.onCreate(SignUpActivity.this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();
                ((SignUpPresenter) presenter).regularSignUp(name, email, password, confirm_password);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fbLogin(SignUpActivity.this);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.twLogin(SignUpActivity.this);
            }
        });

    }
    @Override
    public void success(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        signupSuccessful();
    }

    @Override
    public void failure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupSuccessful() {
        //show set username fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        SetUserNameDialogFragment fragment = new SetUserNameDialogFragment();
        fragment.show(fragmentManager, "sadas");
       /* Intent intent = new Intent(SignUpActivity.this, ParentActivity.class);
        startActivity(intent);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getBack() {
        Intent intent = new Intent(SignUpActivity.this, ParentActivity.class);
        startActivity(intent);
    }
}