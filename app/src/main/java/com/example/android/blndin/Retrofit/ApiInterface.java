package com.example.android.blndin.Retrofit;


import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.CheckHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.CreateHangoutResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.RelatedMembersResponse;

import com.example.android.blndin.Features.Auth.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Auth.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.Auth.SignUp.SetUserName.SetUserNameResponse;
import com.example.android.blndin.Features.HangoutProfile.Model.HangoutProfileDetailsResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.RelatedMembersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
