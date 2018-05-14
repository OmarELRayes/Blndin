package com.example.android.blndin.Retrofit;

import com.example.android.blndin.Features.HangoutProfile.Model.CommentsResponse;
import com.example.android.blndin.Features.Auth.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Auth.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.Auth.SignUp.SetUserName.SetUserNameResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.AddChatMessageResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileChatResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileDetailsResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfilePostsResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.NormalPostResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.CheckHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.CreateHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.Hangout.Model.RelatedMembersResponse;
import com.example.android.blndin.Features.Invites.Model.InviteHangoutResponse;
import com.example.android.blndin.Features.Invites.Model.InviteSquadResponse;
import com.example.android.blndin.Features.Newsfeed.model.NewsfeedResponse;
import com.example.android.blndin.Features.Profile.Model.ProfileDetailsResponse;
import com.example.android.blndin.Features.Profile.Model.ProfilePostsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by LeGenÐ on 5/11/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("auth/register/firebase")
    Call<LoginResponse> firebaseLogin(@Field("auth_id") String fbid, @Field("name") String name, @Field("email") String email);

    @FormUrlEncoded
    @POST("auth/register/regular")
    Call<SignUpResponse> regularSignUp(@Field("name") String name, @Field("email") String email, @Field("lat") String lat, @Field("lng") String lng, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/login/regular")
    Call<LoginResponse> regularLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("hangouts/activities")
    Call<ActivitiesResponse>getActivities(@Field("token")String token);

    @FormUrlEncoded
    @POST("hangouts/map/nearby")
    Call<RelatedMembersResponse> getRelatedMembers(@Field("token") String token,@Field("lat")String lat,@Field("lng")String lng);

    @FormUrlEncoded
    @POST("settings/set-username")
    Call<SetUserNameResponse> setUserName(@Field("username") String username, @Field("token") String token);

    @FormUrlEncoded
    @POST("hangouts/create")
    Call<CreateHangoutResponse> createHangout(@Field("token")String token, @Field("title")String title, @Field("activity_id")String activity_id
            , @Field("sub_activity")String sub_activity, @Field("users")String users, @Field("message")String message,@Field("address")String address,@Field("lat")String lat,@Field("lng")String lng);
    @FormUrlEncoded
    @POST("hangouts/show")
    Call<HangoutProfileDetailsResponse> getHangoutDetails(@Field("hangout_id") String id, @Field("token") String token);

    @FormUrlEncoded
    @POST("hangouts/status")
    Call<CheckHangoutResponse>checkHangout(@Field("token")String token,@Field("hangout_id")String hangout_id);

    @FormUrlEncoded
    @POST("hangouts/posts")
    Call<HangoutProfilePostsResponse>getHangoutPost(@Field("token")String token, @Field("hangout_id")String hangout_id, @Query("page") String page);

    @FormUrlEncoded
    @POST("hangouts/posts/like")
    Call<NormalPostResponse> likeHangoutPost(@Field("token")String token,@Field("post_id")String post_id);

    @FormUrlEncoded
    @POST("hangouts/posts/unlike")
    Call<NormalPostResponse> unlikeHangoutPost(@Field("token")String token,@Field("post_id")String post_id);

    @FormUrlEncoded
    @POST("hangouts/posts/comments")
    Call<CommentsResponse>getHangoutPostComments(@Field("token")String token,@Field("post_id")String post_id,@Query("page")String page);

    @FormUrlEncoded
    @POST("hangouts/posts/comments/comment")
    Call<NormalPostResponse>add_comment_hangout(@Field("token")String token,@Field("post_id")String post_id,@Field("comment")String comment);
    
    @FormUrlEncoded
    @POST("hangouts/chat")
    Call<HangoutProfileChatResponse> getHangoutChat(@Field("token") String token, @Field("hangout_id") String hangout_id, @Query("page") String page);

    @FormUrlEncoded
    @POST("hangouts/chat/create")
    Call<AddChatMessageResponse> addMessage(@Field("token") String token, @Field("hangout_id") String hangout_id, @Field("message") String message);

    @FormUrlEncoded
    @POST("squads/create")
    Call<NormalPostResponse> createSquad(@Field("token")String token, @Field("hangout_id") String hangout_id,@Field("title")String title,@Field("description")String description,@Field("message")String message,@Field("image")String image);

    @FormUrlEncoded
    @POST("news-feed/discover")
    Call<NewsfeedResponse> getDiscoverNewsfeed(@Field("token")String token,@Query("page")String page);

    @FormUrlEncoded
    @POST("news-feed/wall")
    Call<NewsfeedResponse>getWallNewsfeed(@Field("token")String token,@Query("page")String page);

    @FormUrlEncoded
    @POST("profile/invites/hangouts")
    Call<InviteHangoutResponse> getInvitesHangout(@Field("token")String token,@Query("page")String page);


    @FormUrlEncoded
    @POST("profile/invites/squads")
    Call<InviteSquadResponse>getInvitesSquad(@Field("token")String token,@Query("page")String page);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileDetailsResponse>getProfileDetials(@Field("token")String token);

    @FormUrlEncoded
    @POST("profile/posts")
    Call<ProfilePostsResponse>getProfilePosts(@Field("token")String token,@Query("page")String page);

    @FormUrlEncoded
    @POST("profile/invites/hangouts/accept")
    Call<NormalPostResponse>acceptHangoutInvite(@Field("token")String token,@Field("invite_id")String invite_id);

    @FormUrlEncoded
    @POST("profile/invites/hangouts/decline")
    Call<NormalPostResponse>declineHangoutInvite(@Field("token")String token,@Field("invite_id")String invite_id);

    @FormUrlEncoded
    @POST("profile/invites/squads/accept")
    Call<NormalPostResponse>acceptSquadInvite(@Field("token")String token,@Field("invite_id")String invite_id);

    @FormUrlEncoded
    @POST("profile/invites/squads/decline")
    Call<NormalPostResponse>declineSquadInvite(@Field("token")String token,@Field("invite_id")String invite_id);
//
}
