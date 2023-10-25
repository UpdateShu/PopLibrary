package com.geekbrains.poplibrary.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.poplibrary.R
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.ui.fragment.UserInfoFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val GIT_USER = "GIT_USER"
private val githubUser: GithubUser = GithubUser("14059414", "UpdateShu", "User", false, "https://avatars.githubusercontent.com/u/14059414?v=4", "https://api.github.com/users/UpdateShu/repos", "https://api.github.com/users/UpdateShu/followers", "https://api.github.com/users/UpdateShu/following{/other_user}")

@RunWith(AndroidJUnit4::class)
class UserInfoFragmentEspressoTest {
    private lateinit var scenario: FragmentScenario<UserInfoFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer()
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun fragment_TestBundle(){
        val fragmentArgs = bundleOf(GIT_USER to githubUser)
        scenario = launchFragmentInContainer<UserInfoFragment>(themeResId = R.style.Theme_PopLibrary, fragmentArgs = fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = ViewAssertions.matches(ViewMatchers.withText("UpdateShu"))
        Espresso.onView(withId(R.id.user_login)).check(assertion)
    }
}