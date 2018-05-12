package com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.View;

import android.view.View;

import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

/**
 * Created by Luffy on 5/12/2018.
 */

public interface RelatedMembersView {

    void successfulResponseRelatedMembers(ArrayList<User> members);
    void successfulResponseActivites(ActivitiesResponse activitiesResponse);
    void failureResponse(String status,String message);
    View findViewById();
}
