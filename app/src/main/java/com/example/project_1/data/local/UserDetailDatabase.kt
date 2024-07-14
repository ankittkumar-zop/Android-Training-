package com.example.project_1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDetailData::class] , version = 1 )

abstract class UserDetailDatabase : RoomDatabase(){

    abstract fun userDetailDao() : UserDetailDao

    companion object{
        @Volatile
        private var INSTANCE : UserDetailDatabase? = null

        fun getDatabase( context: Context): UserDetailDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDetailDatabase::class.java,
                    "user_detail_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}