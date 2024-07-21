package com.example.project_1.di

import android.content.Context
import androidx.room.Room
import com.example.project_1.data.local.UserDetailDatabase
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
    fun provideDatabase(@ApplicationContext context: Context) : UserDetailDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            UserDetailDatabase::class.java,
            "user_detail_database"
        ).build()
    }

    @Provides
    fun provideUserDetailDao(database: UserDetailDatabase) : UserDetailDao{
        return database.userDetailDao()
    }
}