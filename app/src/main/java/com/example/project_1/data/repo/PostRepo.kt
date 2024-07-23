package com.example.project_1.data.repo

import androidx.lifecycle.LiveData
import com.example.project_1.data.local.showPost.ShowPostDao
import com.example.project_1.data.remote.ApiCall
import com.example.project_1.data.remote.showPost.ShowPostData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepo @Inject constructor(
    private val apiCall: ApiCall, private val showPostDao: ShowPostDao
) {

    fun liveData(): LiveData<List<ShowPostData>> = showPostDao.getAllPost()

    suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            val response = apiCall.getPost()
            if (response.isSuccessful) {
                response.body()?.let { posts ->
                    showPostDao.insertData(posts)
                }
            }
        }
    }

    suspend fun toggle(postId: Int) {
        withContext(Dispatchers.IO) {
            showPostDao.toggle(postId)
        }
    }
}