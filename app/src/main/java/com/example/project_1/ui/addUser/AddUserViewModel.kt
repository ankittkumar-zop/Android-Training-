package com.example.project_1.ui.addUser

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.data.local.userDetail.UserDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddUserViewModel(context: Context) : ViewModel() {
    private val userDao = UserDetailDatabase.getDatabase(context).userDetailDao()

    fun validateUser(userId: String, username: String, phone: String, onResult: (Boolean) -> Unit) {

        viewModelScope.launch {
            val isSuccess =
                if (userId.isNotBlank() && username.isNotBlank() && phone.isNotBlank()) {
                    val user = UserDetailData(userId, username, phone)
                    withContext(Dispatchers.IO) {
                        userDao.insertUserDetail(user)
                    }
                    true
                } else {
                    false
                }
            onResult(isSuccess)
        }
    }
}







