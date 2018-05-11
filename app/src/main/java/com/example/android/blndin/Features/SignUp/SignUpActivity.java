package com.example.android.blndin.Features.SignUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.blndin.Features.SignUp.Presenter.SignUpPresenter;
import com.example.android.blndin.Features.SignUp.View.SignUpView;
import com.example.android.blndin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

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

    SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        presenter = new SignUpPresenterImp(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();
                presenter.regularSignUp(name, email, password, confirm_password);
                /*Intent intent = new Intent(SignUpActivity.this, CompleteProfileActivity.class);
                startActivity(intent);*/
            }
        });


    }

    @Override
    public void successfulSignUp(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failedSignUp() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signUpClick() {

    }
}
