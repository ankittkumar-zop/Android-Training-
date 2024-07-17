package com.example.project_1.ui.showPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_1.data.repo.PostRepo

class ShowPostViewModelFactory(private val postRepo: PostRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowPostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShowPostViewModel(postRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}