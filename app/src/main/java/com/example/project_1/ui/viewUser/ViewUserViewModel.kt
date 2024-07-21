package com.example.project_1.ui.viewUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.data.local.userDetail.UserDetailData
import kotlinx.coroutines.launch

class ViewUserViewModel(private val userDao : UserDetailDao) : ViewModel(){
    val users = userDao.getDetails()

    fun getAllUsers() : List<UserDetailData>{
        return userDao.getDetails()
    }

    fun deleteUserById(userId : String){
        viewModelScope.launch {
            userDao.deleteUser(userId)
        }
    }
}
