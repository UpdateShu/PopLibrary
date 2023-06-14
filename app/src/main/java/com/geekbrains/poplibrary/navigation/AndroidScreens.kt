package com.geekbrains.poplibrary.navigation

import android.os.Bundle
import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.geekbrains.poplibrary.ui.fragment.UserInfoFragment
import com.geekbrains.poplibrary.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }
    override fun userInfo(user: GithubUser) = FragmentScreen {
        UserInfoFragment.newInstance(Bundle().also { bundle ->
            bundle.putParcelable(UserInfoFragment.GIT_USER, user) })
    }
}