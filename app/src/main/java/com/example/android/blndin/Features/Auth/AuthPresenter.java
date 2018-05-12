package com.example.android.blndin.Features.Auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.facebook.AccessToken;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public interface AuthPresenter {

    void onCreate(Context context);

    void fbLogin(final Activity activity);

    void handleFacebookAccessToken(AccessToken token, Activity activity);

    void twLogin(final Activity activity);

    void handleTwitterSession(TwitterSession session, Activity activity);

    public void firebaseLogin(final String uid, final String name, final String email);

    public void saveToken(String token);

    public void onActivityResult(int requestCode, int resultCode, Intent data);

}
