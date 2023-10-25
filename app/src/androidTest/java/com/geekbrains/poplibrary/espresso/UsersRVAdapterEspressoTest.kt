package com.geekbrains.poplibrary.espresso

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.poplibrary.R
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.ui.activity.MainActivity
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import com.geekbrains.poplibrary.ui.fragment.UsersFragment
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersRVAdapterEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun recyclerView_ScrollToTest() {
        scenario.moveToState(Lifecycle.State.CREATED)

        Espresso.onView(withId(R.id.r_users)).perform(
            RecyclerViewActions.scrollTo<UsersRVAdapter.ViewHolder>(
                ViewMatchers.hasDescendant(ViewMatchers.withText("login30"))
            )
        )
    }

    @Test
    fun recyclerView_ActionOnItemAtPositionTest() {

        Espresso.onView(withId(R.id.r_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UsersRVAdapter.ViewHolder>(
                20, ViewActions.click()
            )
        )
    }

    @Test
    fun recyclerView_CustomActionOnItemAtPositionTest() {

        Espresso.onView(withId(R.id.r_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UsersRVAdapter.ViewHolder>(
                20, tapOnItemWithId(R.id.tv_user_login)
            )
        )
    }

    private fun tapOnItemWithId(id: Int) = object : ViewAction {
        override fun getConstraints(): Matcher<View>? = null

        override fun getDescription(): String = "Метод tapOnItemWithId по элементу $id"

        override fun perform(uiController: UiController?, view: View?) {
            val v = view?.findViewById(id) as View
            v.performClick()
        }
    }
}