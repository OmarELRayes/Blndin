package com.example.android.blndin.Features.HangoutProfile.View;

/**
 * Created by Luffy on 5/13/2018.
 */

public interface HangoutPostCommentsView {
    void successfulResponseComments(String message);
    void failureResponseComments(String message);
    void setFocus(int size);
    void stopRefreshing();
}
