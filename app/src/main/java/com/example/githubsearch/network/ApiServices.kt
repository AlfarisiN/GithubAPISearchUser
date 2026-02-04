package com.example.githubsearch.network

import com.example.githubsearch.model.ResponseUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ResponseUsers
}