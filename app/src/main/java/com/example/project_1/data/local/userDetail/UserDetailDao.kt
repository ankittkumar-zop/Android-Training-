package com.example.project_1.data.local.userDetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDetailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserDetail(userDetail: UserDetailData)

    @Query("SELECT * FROM user_detail_table")
    fun getDetails() : List<UserDetailData>

    @Query("DELETE FROM user_detail_table WHERE userId = :userId")
    fun deleteUser(userId : String)
}