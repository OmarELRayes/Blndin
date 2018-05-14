package com.example.android.blndin.Features.Profile.View;

import com.example.android.blndin.Features.Profile.Model.ProfileDetailsResponse;

/**
 * Created by Luffy on 5/14/2018.
 */

public interface ProfileDetailsView {
    void successfullResponse(ProfileDetailsResponse data,String message);
    void failureResponse(String message);
}
