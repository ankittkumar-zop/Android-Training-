package com.example.project_1.ui.showPost

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.remote.showPost.ShowPostData
import com.example.project_1.data.repo.PostRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowPostViewModel @Inject constructor(
    private val postRepo: PostRepo
) : ViewModel() {

    val posts: LiveData<List<ShowPostData>> = postRepo.liveData()

    fun fetchPost() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                postRepo.fetchData()
            }
        }
    }

    fun toggleLike(postId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                postRepo.toggle(postId)
            }
        }
    }
}
