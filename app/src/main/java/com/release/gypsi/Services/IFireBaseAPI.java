package com.release.gypsi.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IFireBaseAPI {

    @GET("/users.json")
    Call<String> getAllUsersAsJsonString();

    @GET
    Call<String> getUserFriendsListAsJsonString(@Url String url);

    @GET
    Call<String> getSingleUserByEmail(@Url String url);

}
