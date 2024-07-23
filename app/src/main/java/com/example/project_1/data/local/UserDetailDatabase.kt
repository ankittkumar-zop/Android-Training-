package com.example.project_1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_1.data.local.showPost.ShowPostDao
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.data.local.userDetail.UserDetailData
import com.example.project_1.data.remote.showPost.ShowPostData
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [UserDetailData::class, ShowPostData::class],
    version = 9,
    exportSchema = false
)
abstract class UserDetailDatabase : RoomDatabase() {

    abstract fun userDetailDao(): UserDetailDao
    abstract fun showPostDao(): ShowPostDao

    companion object {
        @Volatile
        private var INSTANCE: UserDetailDatabase? = null

        fun getDatabase(@ApplicationContext context: Context): UserDetailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDetailDatabase::class.java,
                    "user_detail_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}