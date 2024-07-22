package com.example.project_1.data.remote

import com.example.project_1.data.remote.showPost.ShowPostData
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {
    @GET("photos")
    fun getPost(): Call<List<ShowPostData>>

    @GET("users")
    fun getUsers(): Call<List<ApiDataClass>>
}