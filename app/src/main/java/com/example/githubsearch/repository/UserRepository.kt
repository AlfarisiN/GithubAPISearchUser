package com.example.githubsearch.repository

import com.example.githubsearch.local.UserDao
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.model.ItemUser
import com.example.githubsearch.model.ResponseUsers
import com.example.githubsearch.network.ApiServices
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
}