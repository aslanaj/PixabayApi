package com.example.pixabayapi.remote

import com.example.pixabayapi.PixabayModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    fun getImages(
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 3,
        @Query("key") key: String = "35788201-8faf2ad320e73db3ee5de6d07"
    ): Call<PixabayModel>
}