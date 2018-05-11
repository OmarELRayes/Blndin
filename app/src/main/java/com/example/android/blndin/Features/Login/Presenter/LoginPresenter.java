package com.example.android.blndin.Features.Login.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public interface LoginPresenter {

    // check sharedPreferences
    void onResume();

    void onCreate(Context context);

    // regular login
    void regularLogin(String username, String password);

    //facebook login
    void fbLogin(Activity activity);

    //twitter login
    void twLogin(Activity activity);

    void onActivityResult(int requestCode, int resultCode, Intent data);

}
