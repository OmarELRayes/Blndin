package com.example.android.blndin.Features.Login.Presenter;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public interface LoginPresenter {

    // check sharedPreferences
    void onResume();

    // regular login
    void regularLogin(String username, String password);

    //facebook login
    void fbLogin();

    //twitter login
    void twLogin();

}
