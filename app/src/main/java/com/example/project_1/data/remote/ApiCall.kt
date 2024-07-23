package com.example.project_1.data.remote

import com.example.project_1.data.remote.showPost.ShowPostData
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    @GET("photos")
    suspend fun getPost(): Response<List<ShowPostData>>

    @GET("users")
    suspend fun getUser(): Response<List<ApiDataClass>>
}