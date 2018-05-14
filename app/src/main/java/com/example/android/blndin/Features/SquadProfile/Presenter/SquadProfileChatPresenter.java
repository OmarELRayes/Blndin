package com.example.android.blndin.Features.SquadProfile.Presenter;

import com.example.android.blndin.Features.SquadProfile.Model.SquadProfileChatResponse;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public interface SquadProfileChatPresenter {

    void getMessagesByPage(String token, String squad_id);

    boolean checkPages(SquadProfileChatResponse.Paginator paginator);

    void addChatMessage(String token, String squad_id, String message);
}
