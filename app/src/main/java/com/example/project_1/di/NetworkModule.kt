package com.example.project_1.di

import com.example.project_1.data.remote.ApiCall
import com.example.project_1.data.remote.RetrofitObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : ApiCall{
        return RetrofitObject.getRetroFitInstance()
    }

    @Provides
    @Singleton
    fun provideApiCall(retrofit: Retrofit) : ApiCall{
        return retrofit.create(ApiCall::class.java)
    }

}