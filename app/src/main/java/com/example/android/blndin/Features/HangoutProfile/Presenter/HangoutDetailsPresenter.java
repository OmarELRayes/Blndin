package com.example.android.blndin.Features.HangoutProfile.Presenter;

import android.content.Context;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public interface HangoutDetailsPresenter {
    void getHangoutDetails(String hangoutId, Context context);
    void createSquad(String token,String hangout_id);
}
