package com.example.android.blndin.Features.HangoutProfile.View;

/**
 * Created by LeGenÐ on 5/13/2018.
 */

public interface HangoutChatView {

    void setFocus();

    void success(String message);

    void failure(String message);

    void stopRefreshing();
}
