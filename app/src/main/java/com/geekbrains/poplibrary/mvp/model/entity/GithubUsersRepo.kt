package com.geekbrains.poplibrary.mvp.model.entity

import io.reactivex.rxjava3.core.Observable
import java.lang.RuntimeException
import kotlin.random.Random

class GithubUsersRepo {

    fun getUsers() : List<GithubUser> {
        return users
    }

    fun fromIterable(): Observable<GithubUser> {
        return Observable.fromIterable(users)
    }

    fun create() = Observable.create<GithubUser> { emitter ->
        try {
            for (user in users) {
                randomResultOperation().let {
                    val index = users.indexOf(user)
                    if (it || index < users.count()/2) {
                        emitter.onNext(user)
                    } else {
                        emitter.onError(RuntimeException("Error on " + user.login))
                        return@create
                    }
                }
            }
            emitter.onComplete()
        } catch (t: Throwable) {
            emitter.onError(RuntimeException("Unknown error"))
        }
    }

    private fun randomResultOperation() : Boolean {
        return listOf(true, false, true)[Random.nextInt(2)]
    }

    private val users = listOf (
        GithubUser("","login1"),
        GithubUser("","login2"),
        GithubUser("","login3"),
        GithubUser("","login4"),
        GithubUser("","login5"),
        GithubUser("","login6"),
        GithubUser("","login7"),
        GithubUser("","login8"),
        GithubUser("","login9"),
        GithubUser("","login10")
    )
}