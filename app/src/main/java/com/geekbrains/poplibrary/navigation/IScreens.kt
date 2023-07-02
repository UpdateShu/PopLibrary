package com.geekbrains.poplibrary.navigation

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen
    fun userInfo(user: GithubUser) : Screen
    fun userRepo(repo: GithubRepository) : Screen
}