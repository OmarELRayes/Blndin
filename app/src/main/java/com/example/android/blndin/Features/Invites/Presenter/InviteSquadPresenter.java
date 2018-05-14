package com.example.android.blndin.Features.Invites.Presenter;

import com.example.android.blndin.Features.Invites.Model.InviteSquadResponse;

/**
 * Created by Luffy on 5/14/2018.
 */

public interface InviteSquadPresenter {
    void getInviteSquadByPage(String token);
    boolean checkPages( InviteSquadResponse.Paginator paginator);
}
