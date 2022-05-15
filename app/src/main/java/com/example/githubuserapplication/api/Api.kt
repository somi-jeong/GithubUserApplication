package com.example.githubuserapplication.api

import com.example.githubuserapplication.data.model.DetailUserResponse
import com.example.githubuserapplication.data.model.User
import com.example.githubuserapplication.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token write your personal access token")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token write your personal access token")
    fun getUserDetail(
        @Path("username") username:String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token write your personal access token")
    fun getFollowers(
        @Path("username") username:String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token write your personal access token")
    fun getFollowing(
        @Path("username") username:String
    ): Call<ArrayList<User>>

}