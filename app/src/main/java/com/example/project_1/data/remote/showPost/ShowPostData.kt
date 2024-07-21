package com.example.project_1.data.remote.showPost

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "show_post_table")
data class ShowPostData(
    @PrimaryKey(autoGenerate = true)
    var postId: Int,
    var title: String?,
    var url: String?,
    var id: Int?,
    var isLiked: Boolean = false
)
