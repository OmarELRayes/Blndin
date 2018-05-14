package com.example.android.blndin.Features.HangoutScenario.Hangout.View;

import android.view.View;

import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.ActivitiesResponse;

/**
 * Created by Luffy on 5/12/2018.
 */

public interface RelatedMembersView {

    void successfulResponseRelatedMembers(String status,String message);
    void successfulResponseActivites(ActivitiesResponse activitiesResponse);
    void failureResponse(String status,String message);
    View findViewById();
    void successfulResponseCreateHangout(String message);
    void successfullResponseCheckHangout(String status,String hangout_id);
}
