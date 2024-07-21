package com.example.project_1.ui.viewUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.data.local.userDetail.UserDetailData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ViewUserViewModel @Inject constructor(
    private val userDao: UserDetailDao
) : ViewModel() {

    fun getAllUsers(onResult: (List<UserDetailData>) -> Unit) {
        viewModelScope.launch {
            val users = userDao.getDetails()
            onResult(users)
        }
    }

    fun deleteUserById(userId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {}
            userDao.deleteUser(userId)
        }
    }
}