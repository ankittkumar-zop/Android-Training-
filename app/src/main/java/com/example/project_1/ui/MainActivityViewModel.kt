package com.example.project_1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.remote.ApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val apiCall: ApiCall
) : ViewModel() {

    private val _apiCallResult = MutableLiveData<String>()
    val apiCallResult: LiveData<String> get() = _apiCallResult

    fun makeApiCall() {
        viewModelScope.launch {
            try {
                val response = apiCall.getUser()
                if (response.isSuccessful) {
                    _apiCallResult.value = "Success"
                } else {
                    _apiCallResult.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _apiCallResult.value = "Failed to fetch"
            }
        }
    }
}
