package com.example.android.blndin.Features.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Features.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Login.Presenter.LoginPresenter;
import com.example.android.blndin.Features.Login.View.LoginView;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public class LoginPresenterImp implements LoginPresenter {


    LoginView view;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;

    public LoginPresenterImp(LoginView view) {
        this.view = view;
    }


    // check sharedPreferneces
    @Override
    public void onResume() {

    }

    @Override
    public void regularLogin(String email, String password) {
        if (validate(email, password)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponse> call = apiInterface.regularLogin(
                    email, password
            );
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    //TODO : Save in SharedPreferences
                    view.loginSuccessful(response.body().getStatus().toString());
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    view.loginFailed();
                }
            });
        }
    }

    @Override
    public void fbLogin(final Activity activity) {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(this.toString(), "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken(), activity);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("5ara", "onError: " + error.toString());
            }
        });
    }

    @Override
    public void twLogin(Activity activity) {
        TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        twitterAuthClient.authorize(activity, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        final TwitterSession sessionData = result.data;
                    }

                    @Override
                    public void failure(TwitterException exception) {

                    }
                }
        );
    }

    boolean validate(String email, String password) {
        if (password.length() > 6 && email.contains("@") && email.contains(".")) {
            return true;
        }
        return false;

    }

    @Override
    public void onCreate(Context context) {
        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();
        Twitter.initialize(context);
    }

    private void handleFacebookAccessToken(AccessToken token, Activity activity) {
        Log.d(this.toString(), "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(this.toString(), "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.fbLoginSuccess(user.getDisplayName() + user.getUid());
                            //Toast.makeText(LoginActivity.this, user.getEmail() + " " +user.getPhotoUrl(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(this.toString(), "signInWithCredential:failure", task.getException());
                            //Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
