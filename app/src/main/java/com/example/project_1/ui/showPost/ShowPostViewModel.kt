package com.example.project_1.ui.showPost
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.remote.showPost.ShowPostData
import com.example.project_1.data.repo.PostRepo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ShowPostViewModel(private val postRepo : PostRepo) : ViewModel(){

    val posts = postRepo.observer()

    fun fetchPost(onResult: (List<ShowPostData>) -> Unit ){
        viewModelScope.launch {
            val data = postRepo.fetchData()
            onResult(data)
        }
    }

    fun toggle(postId : Int){
        viewModelScope.launch {
            postRepo.toggle(postId)
        }
    }
}