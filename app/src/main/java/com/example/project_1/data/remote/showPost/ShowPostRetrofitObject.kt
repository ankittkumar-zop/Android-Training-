package com.example.project_1.data.remote.showPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://jsonplaceholder.typicode.com/"

class ShowPostRetrofitObject {

    fun getShowPostRetrofitObject() : ShowPost{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShowPost::class.java)
    }
}