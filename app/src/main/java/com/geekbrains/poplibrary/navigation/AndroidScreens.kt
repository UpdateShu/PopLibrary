package com.geekbrains.poplibrary.navigation

import android.os.Bundle
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.ui.fragment.UserInfoFragment
import com.geekbrains.poplibrary.ui.fragment.UserRepoFragment
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

    override fun userRepo(repo: GithubRepository) = FragmentScreen {
        UserRepoFragment.newInstance(Bundle().also { bundle ->
            bundle.putParcelable(UserRepoFragment.GIT_USER_REPO, repo)
        })
    }
}