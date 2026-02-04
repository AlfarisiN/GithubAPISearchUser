package com.example.githubsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.model.ItemUser
import com.example.githubsearch.model.ResponseUsers
import com.example.githubsearch.model.UIEvent
import com.example.githubsearch.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

//private val SearchUserViewModel.viewModelScope: Any

class SearchUserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> = _users
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun searchUser(query: String) {

        viewModelScope.launch {
            val (result, message) = repository.searchUsers(query)
            _users.value = result
            _message.value = message
        }
    }
}