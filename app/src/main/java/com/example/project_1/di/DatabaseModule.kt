package com.example.project_1.di

import android.content.Context
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.data.local.showPost.ShowPostDao
import com.example.project_1.data.local.userDetail.UserDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDetailDatabase(@ApplicationContext context: Context): UserDetailDatabase {
        return UserDetailDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDetailDao(database: UserDetailDatabase): UserDetailDao {
        return database.userDetailDao()
    }

    @Provides
    @Singleton
    fun provideShowPostDao(database: UserDetailDatabase): ShowPostDao {
        return database.showPostDao()
    }
}