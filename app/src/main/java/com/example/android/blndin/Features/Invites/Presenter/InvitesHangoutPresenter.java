package com.example.android.blndin.Features.Invites.Presenter;

import com.example.android.blndin.Features.Invites.Model.InviteHangoutResponse;

/**
 * Created by Luffy on 5/14/2018.
 */

public interface InvitesHangoutPresenter {
    void getInvitesHangoutByPage(String token);
    boolean checkPages(InviteHangoutResponse.Paginator paginator);
}
