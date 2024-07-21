package com.example.project_1.data.local.showPost

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project_1.data.remote.showPost.ShowPostData

@Dao
interface ShowPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(showPostData: List<ShowPostData>)

    @Query("SELECT * FROM show_post_table")
    fun getAllPost(): LiveData<List<ShowPostData>>

    @Query(" UPDATE show_post_table SET isLiked = NOT isLiked WHERE postId = :postId;")
    suspend fun toggle(postId: Int)
}