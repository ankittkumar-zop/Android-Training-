package com.example.project_1.ui.addUser

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.project_1.data.local.userDetail.UserDetailData
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ui.MainActivity

class AddUserViewModel(context: Context): ViewModel() {
    private val userDao = UserDetailDatabase.getDatabase(context).userDetailDao()

    fun validateUser(userId : String , username :String, phone :String):Boolean  {
        return if (userId.isNotBlank() && username.isNotBlank() && phone.isNotBlank()){
            val user = UserDetailData(userId, username, phone)
            userDao.insertUserDetail(user)
            true
        }
        else{
            false
        }
    }
}









