package com.example.project_1.ui.addUser

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
class AddUserViewModel @Inject constructor(
    private val userDao: UserDetailDao
) : ViewModel() {

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








