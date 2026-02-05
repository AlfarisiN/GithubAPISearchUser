package com.example.githubsearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubsearch.local.UserDao
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.model.ItemUser
import com.example.githubsearch.model.ResponseUsers
import com.example.githubsearch.network.ApiServices
import com.example.githubsearch.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiServices,
    private val userDao: UserDao
) {
    suspend fun searchUsers(query: String): Pair<List<UserEntity>, String> {

        val localData = userDao.searchUsers(query)
        if (localData.isNotEmpty()) {
            return Pair(localData, "Fetch data from database")
        }
        val response = api.searchUsers(query)

        val users = response.items.map {
            UserEntity(
                id = it.id,
                login = it.login,
                avatar_url = it.avatar_url,
                events_url = it.events_url,
                followers_url = it.followers_url,
                following_url = it.following_url,
                gists_url = it.gists_url,
                gravatar_id = it.gravatar_id,
                html_url = it.html_url,
                node_id = it.node_id,
                organizations_url = it.organizations_url,
                received_events_url = it.received_events_url,
                repos_url = it.repos_url,
                score = it.score,
                site_admin = it.site_admin,
                starred_url = it.starred_url,
                subscriptions_url = it.subscriptions_url,
                type = it.type,
                url = it.url,
                user_view_type = it.user_view_type
            )
        }
        userDao.insertUsers(users)

        return Pair(users, "Not found in database, proceed to get from API")
    }

    suspend fun getUserDetail(id: Int): UserEntity? {
        return userDao.getUserById(id)
    }

//    private val apis = RetrofitClient.api

//    suspend fun searchUser(username: String): LiveData<List<UserEntity>> {
//        val result = MutableLiveData<List<UserEntity>>()
//        apis.searchUsers(username).enqueue(object : Callback<ResponseUsers> {
//            override fun onResponse(call: Call<ResponseUsers>, response: Response<ResponseUsers>) {
//                result.postValue(response.body()?.items ?: emptyList())
//            }
//
//            override fun onFailure(call: Call<ResponseUsers>, t: Throwable) {
//                result.postValue(emptyList())
//            }
//        })
//        return result
//    }
}