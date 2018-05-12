package com.example.android.blndin.Features.HangoutProfile.Presenter;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfilePostsResponse;

/**
 * Created by Luffy on 5/12/2018.
 */

public interface HangoutPostsPresenter {
    void getPostsByPage(String token,String hangout_id);
    boolean checkPages(HangoutProfilePostsResponse.Paginator paginator);
}
