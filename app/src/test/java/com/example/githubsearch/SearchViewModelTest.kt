package com.example.githubsearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubsearch.local.UserEntity
import com.example.githubsearch.repository.UserRepository
import com.example.githubsearch.viewmodel.SearchUserViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SearchUserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: UserRepository
    private lateinit var viewModel: SearchUserViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = SearchUserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchUser emits users from repository`() = runTest {
        val users = listOf(
            UserEntity(
                id = 1,
                login = "alfarisi",
                avatar_url = "",
                type = "User",
                user_view_type = "public",
                score = 1.0,
                site_admin = false,
                url = "", html_url = "", followers_url = "",
                following_url = "", gists_url = "",
                starred_url = "", subscriptions_url = "",
                organizations_url = "", repos_url = "",
                events_url = "", received_events_url = "",
                gravatar_id = "", node_id = ""
            )
        )

        val result = Pair(users, "Fetch data from database")

        whenever(repository.searchUsers("alfarisi")).thenReturn(result)

        viewModel.searchUser("alfarisi")
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(users, viewModel.users.value)
    }
}