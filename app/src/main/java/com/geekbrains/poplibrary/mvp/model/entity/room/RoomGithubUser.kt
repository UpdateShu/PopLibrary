package com.geekbrains.poplibrary.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String?,
    var followersUrl: String? = null,
    var followingUrl: String? = null,
    var reposUrl: String?
)