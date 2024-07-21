package com.example.project_1.data.remote
import com.example.project_1.data.remote.showPost.ShowPostData
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall  {
    @GET("users")
    fun getUsers() : Call<List<ApiDataClass>>
    @GET("photos")
    fun getPosts(): Call<List<ShowPostData>>
}