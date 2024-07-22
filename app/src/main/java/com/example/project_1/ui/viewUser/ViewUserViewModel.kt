package com.example.project_1.ui.viewUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.data.local.userDetail.UserDetailData
import kotlinx.coroutines.launch

class ViewUserViewModel(private val userDao: UserDetailDao) : ViewModel() {

    fun getAllUsers(onResult: (List<UserDetailData>) -> Unit) {
        viewModelScope.launch {
            val users = userDao.getDetails()
            onResult(users)
        }
    }

    fun deleteUserById(userId: String) {
        viewModelScope.launch {
            userDao.deleteUser(userId)
        }
    }
}