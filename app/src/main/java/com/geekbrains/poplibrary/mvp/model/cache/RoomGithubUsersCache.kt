package com.geekbrains.poplibrary.mvp.model.cache

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.mvp.model.entity.room.RoomGithubUser

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(private val db: Database) : IGithubUsersCache {

    override fun insertUsersToDB(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id,
                user.login,
                user.type ?: "",
                user.siteAdmin,
                user.avatarUrl ?: "",
                user.followersUrl ?: "",
                user.followingUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getUsersFromDB() = Single.fromCallable {
        db.userDao.getAll().map { roomUser -> GithubUser(
            roomUser.id,
            roomUser.login,
            roomUser.type,
            roomUser.siteAdmin,
            roomUser.avatarUrl,
            roomUser.followersUrl,
            roomUser.followingUrl,
            roomUser.reposUrl)
        }
    }.subscribeOn(Schedulers.io())
}
