package com.example.project_1.ui.viewUser

import androidx.lifecycle.ViewModel
import com.example.project_1.data.local.UserDetailDao
import com.example.project_1.data.local.UserDetailData

class ViewUserViewModel(private val userDao : UserDetailDao) : ViewModel(){
    fun getAllUsers() : List<UserDetailData>{
        return userDao.getDetails()
    }

    fun deleteUserById(userId : String){
        userDao.deleteUser(userId)
    }
}
