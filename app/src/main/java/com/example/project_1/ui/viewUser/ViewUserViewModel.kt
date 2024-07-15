package com.example.project_1.ui.viewUser

import androidx.lifecycle.AndroidViewModel
import com.example.project_1.data.local.UserDetailDao
import com.example.project_1.data.local.UserDetailData
import com.example.project_1.data.local.UserDetailDatabase

class ViewUserViewModel(private val userDao : UserDetailDao){
//    private val userDao = UserDetailDatabase.getDatabase(application).userDetailDao()

    fun getAllUsers() : List<UserDetailData>{
        return userDao.getDetails()
    }

}
