package com.geekbrains.poplibrary.navigation

import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen
    fun userInfo(user: GithubUser) : Screen
}