package com.example.project_1.data.remote

import com.example.project_1.UserDetailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCall  {
    @GET("users")
    fun getUsers() : Call<List<ApiDataClass>>
}