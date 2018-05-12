package com.example.android.blndin.Features.HangoutProfile.View;

import com.example.android.blndin.Models.HangoutModel;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public interface HangoutDetailsView {

    void updateUi(HangoutModel hangout);

    void failure(String message);
}
