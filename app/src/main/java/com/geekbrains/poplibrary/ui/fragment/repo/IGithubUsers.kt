package com.geekbrains.poplibrary.ui.fragment.repo

import io.reactivex.rxjava3.core.Single
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser

interface IGithubUsers {
    fun getUsers(): Single<List<GithubUser>>
}