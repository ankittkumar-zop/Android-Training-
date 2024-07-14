package com.example.project_1.data.remote
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall  {
    @GET("users")
    fun getUsers() : Call<List<ApiDataClass>>
}