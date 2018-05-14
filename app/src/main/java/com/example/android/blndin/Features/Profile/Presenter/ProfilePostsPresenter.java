package com.example.android.blndin.Features.Profile.Presenter;

import com.example.android.blndin.Features.Profile.Model.ProfilePostsResponse;

/**
 * Created by Luffy on 5/14/2018.
 */

public interface ProfilePostsPresenter {
    void getPostsByPage(String token);
    boolean checkPage(ProfilePostsResponse.Paginator paginator);
}
