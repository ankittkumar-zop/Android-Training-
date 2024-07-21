package com.example.project_1.data.repo

import androidx.lifecycle.LiveData
import com.example.project_1.data.local.showPost.ShowPostDao
import com.example.project_1.data.remote.ApiCall
import com.example.project_1.data.remote.showPost.ShowPostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepo(private val apiCall : ApiCall , private val showPostDao: ShowPostDao){

    suspend fun fetchData() : List<ShowPostData>{
       return withContext(Dispatchers.IO){
           val response= apiCall.getPost()
           if (response.isSuccessful){
               response.body()?.let { posts ->
                   showPostDao.insertData(posts)
                   posts
               }?: emptyList()
           } else{
               emptyList()
           }
       }
    }

    fun observer() = showPostDao.getAllPost()

    suspend fun toggle(postId : Int){
        withContext(Dispatchers.IO){
            showPostDao.toggle(postId)
        }

    }
}