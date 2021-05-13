package com.sky.catnews

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sky.catnews.ui.storyscreen.StoryFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class StoryFragmentTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(HiltTestActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<StoryFragment>(
            fragmentArgs = bundleOf("storyId" to ""),
            instantiate = { StoryFragment() }
        )
    }

    @After
    fun cleanUp() {
        activityScenarioRule.scenario.close()
    }

    @Test
    fun testHeroViewIsDisplayed() {
        runBlocking { delay(3000L) }
        onView(withId(R.id.incHero))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvHeroTitle))
            .check(matches(isDisplayed()))
            .check(matches(withText("Cat story headline")))
    }

    @Test
    fun testStoryContentsDisplayed() {
        runBlocking { delay(3000L) }
        onView(withId(R.id.rvStoryContent))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withId(R.id.tvParagraph))))
    }
}
