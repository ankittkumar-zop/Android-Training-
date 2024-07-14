package com.example.project_1.ui.addUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.project_1.data.local.UserDetailDao
import com.example.project_1.data.local.UserDetailData
import com.example.project_1.data.local.UserDetailDatabase

class AddUserViewModel(application : Application): AndroidViewModel(application){
    private val userDao = UserDetailDatabase.getDatabase(application).userDetailDao()

    fun addUser( user : UserDetailData){
        userDao.insertUserDetail(user)
    }

}