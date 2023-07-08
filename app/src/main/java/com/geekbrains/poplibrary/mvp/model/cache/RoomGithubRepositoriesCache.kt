package com.geekbrains.poplibrary.mvp.model.cache

import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.mvp.model.entity.room.RoomGithubRepository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(private val db: Database) : IGithubRepositoriesCache{
    override fun insertRepositoriesToDB(
        user: GithubUser,
        repositories: List<GithubRepository>
    ) = Completable.fromAction {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No users!")
        val roomRepositories = repositories.map { repository ->
            RoomGithubRepository(
                repository.id,
                repository.name ?: "",
                repository.createdAt ?: "",
                repository.forksCount ?: 0,
                repository.forksUrl,
                roomUser.id)
        }
        db.repositoryDao.insert(roomRepositories)
    }.subscribeOn(Schedulers.io())

    override fun getRepositoriesFromDB(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
            ?: throw RuntimeException("No users!")

        return@fromCallable db.repositoryDao.findForUser(roomUser.id)
            .map { roomRepository ->
                GithubRepository(
                    roomRepository.id,
                    roomRepository.name,
                    roomRepository.createdAt,
                    roomUser.id,
                    roomRepository.forksCount,
                    roomRepository.forksUrl)
            }
    }.subscribeOn(Schedulers.io())
}