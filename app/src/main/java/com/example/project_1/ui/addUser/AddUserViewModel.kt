package com.example.project_1.ui.addUser

import android.app.Application
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.project_1.R
import com.example.project_1.ViewUserDetailFragment
import com.example.project_1.data.local.UserDetailData
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ui.MainActivity

lateinit var mainActivity: MainActivity

class AddUserViewModel(application : Application): AndroidViewModel(application) {
    private val userDao = UserDetailDatabase.getDatabase(application).userDetailDao()

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









