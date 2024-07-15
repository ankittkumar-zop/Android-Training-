package com.example.project_1.data.remote.showPost

import retrofit2.Call
import retrofit2.http.GET


interface ShowPost {

    @GET("photos")
    fun getPost() : Call<List<ShowPostData>>
}