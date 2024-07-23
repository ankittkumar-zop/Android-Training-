package com.example.project_1.data.local.userDetail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_detail_table")
data class UserDetailData(
    @PrimaryKey
    var userId: String,
    var name: String,
    var phone: String
)