package com.example.android.blndin.Features.Auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.blndin.Features.Auth.Login.Model.LoginResponse;
import com.example.android.blndin.Retrofit.ApiClient;
import com.example.android.blndin.Retrofit.ApiInterface;
import com.example.android.blndin.Util.SharedPreferencesHelper;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
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
 * Created by LeGenÐ on 5/12/2018.
 */

public class AuthPresenterImp implements AuthPresenter {

    AuthView view;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    TwitterAuthClient twitterAuthClient;
    Context context;

    public AuthPresenterImp(AuthView view) {
        this.view = view;
    }

    @Override
    public void onCreate(Context context) {
        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();
        Twitter.initialize(context);
        this.context = context;
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
    public void handleFacebookAccessToken(AccessToken token, Activity activity) {
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
                            view.success(user.getDisplayName() + user.getUid());
                            firebaseLogin(user.getUid(), user.getDisplayName(), user.getEmail());
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
    public void twLogin(final Activity activity) {
        twitterAuthClient = new TwitterAuthClient();
        twitterAuthClient.authorize(activity, new com.twitter.sdk.android.core.Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        Log.d(this.toString(), "twitterLogin:success" + result);
                        handleTwitterSession(result.data, activity);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(activity, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void handleTwitterSession(TwitterSession session, Activity activity) {
        Log.d(this.toString(), "handleTwitterSession:" + session);
        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(this.toString(), "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("authss", "onComplete: " + user.getUid());
                            ;
                            firebaseLogin(user.getUid(), user.getDisplayName(), user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(this.toString(), "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    @Override
    public void firebaseLogin(final String uid, final String name, final String email) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiInterface.firebaseLogin(
                uid, name, email, Double.toString(getUserLocation().latitude), Double.toString(getUserLocation().longitude)
        );
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //view.loginSuccessMessage(response.body().getStatus().toString());
                Log.d("kappa", "onResponse: " + uid + " " + name + " " + email);
                if (response.body().getStatus().equals("200")) {
                    saveToken(response.body().getToken(),response.body().getPayload().getUsers().getUsername());
                    view.success(response.body().getStatus());
                } else
                    view.failure(response.body().getStatus() + " " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.failure(t.getMessage());
            }
        });
    }

    @Override
    public void saveToken(String token,String name) {
        SharedPreferencesHelper.storeDataToSharedPref(context, token, "token");
        SharedPreferencesHelper.storeDataToSharedPref(context, name, "username");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mCallbackManager != null)
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (twitterAuthClient != null)
            twitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    public LatLng getUserLocation() {
        return new LatLng(31.042918, 31.354877);
    }
}
