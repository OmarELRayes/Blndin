package com.example.android.blndin.Features.Auth.SignUp.Presenter;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public interface SignUpPresenter {

    LatLng getUserLocation();

    void regularSignUp(String name, String email, String password, String cPassword);

}
