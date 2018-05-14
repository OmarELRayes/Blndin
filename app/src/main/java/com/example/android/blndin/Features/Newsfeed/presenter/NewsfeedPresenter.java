package com.example.android.blndin.Features.Newsfeed.presenter;

import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfilePostsResponse;
import com.example.android.blndin.Features.Newsfeed.model.NewsfeedResponse;

/**
 * Created by Luffy on 5/14/2018.
 */

public interface NewsfeedPresenter {
    void getNewsfeedsDiscoverByPage(String token);
    void getNewsfeedWallByPage(String token);
    boolean checkPages(NewsfeedResponse.Paginator paginator);
}
