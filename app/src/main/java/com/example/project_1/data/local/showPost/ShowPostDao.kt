package com.example.project_1.data.local.showPost

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.project_1.data.remote.showPost.ShowPostData


@Dao
interface ShowPostDao {

    @Insert
    fun insertData(ShowPostData : List<ShowPostData>)

    @Query("SELECT * FROM ShowPostData")
    fun getAllPost() : LiveData<List<ShowPostData>>


}