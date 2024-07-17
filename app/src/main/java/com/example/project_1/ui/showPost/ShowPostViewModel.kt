package com.example.project_1.ui.showPost

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.project_1.data.remote.showPost.ShowPostData
import com.example.project_1.data.remote.showPost.ShowPostRetrofitObject
import com.example.project_1.data.repo.PostRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPostViewModel(private val postRepo : PostRepo) : ViewModel(){

    init {
        downloadPost()
    }

    private fun downloadPost() {
        postRepo.fetchData()
    }

    fun liveData() : LiveData<List<ShowPostData>> = postRepo.liveData()

    fun toggle(postId : Int){
        postRepo.toggle(postId)
    }
}