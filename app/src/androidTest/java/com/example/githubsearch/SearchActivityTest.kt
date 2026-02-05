package com.example.githubsearch

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubsearch.adapter.UserListAdapter
import com.example.githubsearch.view.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun searchField_isDisplayed() {
        onView(withId(R.id.etSearchUser))
            .check(matches(isDisplayed()))
    }

    @Test
    fun typingSearchAndPressSearch_works() {
        // Type search query
        onView(withId(R.id.etSearchUser))
            .perform(typeText("alfarisi"), pressImeActionButton())

        closeAlertDialogIfExists()
        closeAlertDialogIfExists()

        // Wait until RecyclerView has at least 1 item
        onView(withId(R.id.recyclerUser))
            .check(matches(hasMinimumChildCount(1)))

        // Click first item
        onView(withId(R.id.recyclerUser))
            .perform(RecyclerViewActions.actionOnItemAtPosition<UserListAdapter.UserViewHolder>(0, click()))
    }

    fun closeAlertDialogIfExists() {
        onView(withText("OK"))
            .inRoot(isDialog())
            .perform(click())
    }
}