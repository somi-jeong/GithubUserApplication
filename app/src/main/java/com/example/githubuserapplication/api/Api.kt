package com.example.githubuserapplication.api

import com.example.githubuserapplication.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}