package com.szareckii.popularlibraries

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.szareckii.popularlibraries.ui.adapter.RepositoryRvAdapter
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import com.szareckii.popularlibraries.ui.fragment.UserFragment
import com.szareckii.popularlibraries.ui.fragment.UsersFragment
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRecyclerViewTest {

    private lateinit var scenario: FragmentScenario<UsersFragment>

    @Before
    fun setup() {
//        scenario = launchFragmentInContainer()
        scenario = launchFragmentInContainer(themeResId = R.style.ThemePopularLibraries)
    }

    @Test
    fun fragmentUsers_ScrollTo() {
        onView(withId(R.id.rv_users))
            .perform(
                RecyclerViewActions.scrollTo<UsersRvAdapter.ViewHolder>(
                    hasDescendant(withText("Login: 42"))
                )
            )
    }

    @Test
    fun fragmentUsers_PerformClickAtPosition() {
        onView(withId(R.id.rv_users))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UsersRvAdapter.ViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun fragmentUsers_PerformClickOnItem() {
        onView(withId(R.id.rv_users))
            .perform(
                RecyclerViewActions.scrollTo<UsersRvAdapter.ViewHolder>(
                    hasDescendant(withText("Login: 50"))
                )
            )

        onView(withId(R.id.rv_users))
            .perform(
                RecyclerViewActions.actionOnItem<UsersRvAdapter.ViewHolder>(
                    hasDescendant(withText("Login: 42")),
                    click()
                )
            )
    }
}