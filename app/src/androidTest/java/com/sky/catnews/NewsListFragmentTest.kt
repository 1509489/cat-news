package com.sky.catnews

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sky.catnews.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsListFragmentTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @After
    fun cleanUp() {
        activityScenarioRule.scenario.close()
    }

    @Test
    fun testNewsListDisplayed() {
        onView(withId(R.id.pbLoading)).check(matches(isDisplayed()))
        // Delay is need because I added a delay in the
        // mock network service to simulate network call
        runBlocking { delay(2000L) }
        onView(withId(R.id.rvNewsList))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun testNewsStoryIsClickable() {
        onView(withId(R.id.pbLoading)).check(matches(isDisplayed()))
        // Delay is need because I added a delay in the
        // mock network service to simulate network call
        runBlocking { delay(2000L) }
        onView(withId(R.id.rvNewsList))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    3,
                    click()
                )
            )
        runBlocking { delay(2000L) }
        onView(withId(R.id.rvStoryContent)).check(matches(isDisplayed()))
    }
}
