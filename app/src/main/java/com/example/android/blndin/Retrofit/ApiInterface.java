package com.example.android.blndin.Retrofit;

import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.ActivitiesResponse;
import com.example.android.blndin.Features.HangoutScenario.GetRelatedMembers.Model.RelatedMembersResponse;
import com.example.android.blndin.Features.Login.Model.LoginResponse;
import com.example.android.blndin.Features.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.Auth.Login.Model.LoginResponse;
import com.example.android.blndin.Features.Auth.SignUp.Model.SignUpResponse;
import com.example.android.blndin.Features.Auth.SignUp.SetUserName.SetUserNameResponse;

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



}
