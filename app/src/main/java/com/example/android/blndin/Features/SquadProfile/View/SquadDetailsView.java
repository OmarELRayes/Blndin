package com.example.android.blndin.Features.SquadProfile.View;

import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileDetailsResponse.Payload.Squad.Member;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/14/2018.
 */

public interface SquadDetailsView {

    void bindMembers(ArrayList<Member> members);

    void bindDetails(String image, String title, String desc, String admin, String createdAt);

    void bindNews();
}
