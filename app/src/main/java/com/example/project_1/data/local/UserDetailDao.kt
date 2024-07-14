package com.example.project_1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDetailDao {
    @Insert
    fun insertUserDetail(userDetail: UserDetailData)

    @Query("SELECT * FROM user_detail_table")
    fun getDetails() : List<UserDetailData>

    @Delete
    fun deleteUser(user: UserDetailData)

}