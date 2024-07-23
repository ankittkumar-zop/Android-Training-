package com.example.project_1.ui.viewUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _allUsers = MutableLiveData<List<UserDetailData>>()
    val allUsers: LiveData<List<UserDetailData>> get() = _allUsers

    fun getAllUsers() {
        viewModelScope.launch {
            _allUsers.postValue(userDao.getDetails())
        }
    }

    fun deleteUserById(userId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {}
            userDao.deleteUser(userId)
        }
    }
}