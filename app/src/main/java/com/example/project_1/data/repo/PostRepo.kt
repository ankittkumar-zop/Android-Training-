package com.example.project_1.data.repo

import androidx.lifecycle.LiveData
import com.example.project_1.data.local.showPost.ShowPostDao
import com.example.project_1.data.remote.ApiCall
import com.example.project_1.data.remote.showPost.ShowPostData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepo(private val apiCall : ApiCall , private val showPostDao: ShowPostDao){

    fun fetchData(){
        val posts = apiCall.getPost()
        posts.enqueue(object : Callback<List<ShowPostData>>{
            override fun onResponse(
                call: Call<List<ShowPostData>>,
                response: Response<List<ShowPostData>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { posts ->
                        showPostDao.insertData(posts)
                    }
                }
            }
            override fun onFailure(call: Call<List<ShowPostData>>, t: Throwable) {

            }

        })
    }

    fun liveData(): LiveData<List<ShowPostData>> = showPostDao.getAllPost()
}