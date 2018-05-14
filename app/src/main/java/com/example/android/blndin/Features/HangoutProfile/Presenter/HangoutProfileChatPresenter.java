package com.example.android.blndin.Features.HangoutProfile.Presenter;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileChatResponse;

/**
 * Created by LeGenÐ on 5/12/2018.
 */

public interface HangoutProfileChatPresenter {

    void getMessagesByPage(String token, String hangout_id);

    boolean checkPages(HangoutProfileChatResponse.Paginator paginator);

    void addChatMessage(String token, String hangout_id, String message);
}
