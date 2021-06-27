package com.szareckii.popularlibraries

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.annotations.Expose
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.ui.fragment.UserFragment
import com.szareckii.popularlibraries.ui.fragment.UsersFragment
import kotlinx.android.parcel.Parcelize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<UserFragment>
    val user = GithubUser("1", "login", "avatar", "repos")

    @Before
    fun setup() {
        val fragmentArgs = bundleOf("user" to user)
        scenario = launchFragmentInContainer(fragmentArgs, themeResId = R.style.ThemePopularLibraries)
    }

    @Test
    fun fragment_testBundle() {
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = matches(withText(user.login))
        onView(withId(R.id.login_user)).check(assertion)
    }

    @Test
    fun fragment_testSetUserLoginMethod() {
        scenario.onFragment { fragment ->
            fragment.setUserLogin("LoginTest")
        }
        val assertion = matches(withText("LoginTest"))
        onView(withId(R.id.login_user)).check(assertion)
    }

    @Test
    fun fragmentViewLoginUser_IsDisplayed() {
        scenario.onFragment { fragment ->
            fragment.setUserLogin("LoginTest")
        }
        onView(withId(R.id.login_user)).check(matches(isDisplayed()))
    }

    @Test
    fun fragmentViewLoginUser_IsCompletelyDisplayed() {
        scenario.onFragment { fragment ->
            fragment.setUserLogin("LoginTest")
        }
        onView(withId(R.id.login_user)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun fragmentViewLoginUser_AreEffectiveVisible() {
        scenario.onFragment { fragment ->
            fragment.setUserLogin("LoginTest")
        }
        onView(withId(R.id.login_user)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

}

