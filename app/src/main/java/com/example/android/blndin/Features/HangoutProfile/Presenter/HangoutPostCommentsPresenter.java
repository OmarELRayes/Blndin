package com.example.android.blndin.Features.HangoutProfile.Presenter;

import com.example.android.blndin.Features.HangoutProfile.Model.CommentsResponse;

/**
 * Created by Luffy on 5/13/2018.
 */

public interface HangoutPostCommentsPresenter {
    void getCommentsByPage(String token, String post_id);
    void addComment(String token, String post_id, String comment);
    boolean check_page(CommentsResponse.Paginator paginator);
}
