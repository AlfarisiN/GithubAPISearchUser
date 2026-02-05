package com.example.githubsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity> = _user

    fun loadUser(id: Int) {
        viewModelScope.launch {
            repository.getUserDetail(id)?.let {
                _user.postValue(it)
            }
        }
    }
}