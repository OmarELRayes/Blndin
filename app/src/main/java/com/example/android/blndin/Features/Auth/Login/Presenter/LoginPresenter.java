package com.example.android.blndin.Features.Auth.Login.Presenter;

import android.app.Activity;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public interface LoginPresenter {

    void onResume(Activity activity);

    void regularLogin(String username, String password);

}
