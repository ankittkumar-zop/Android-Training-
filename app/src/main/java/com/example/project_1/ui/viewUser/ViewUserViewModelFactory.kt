package com.example.project_1.ui.viewUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_1.data.local.userDetail.UserDetailDao

class ViewUserViewModelFactory(private val userDao: UserDetailDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewUserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewUserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}