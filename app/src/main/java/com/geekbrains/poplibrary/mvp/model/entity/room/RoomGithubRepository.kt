package com.geekbrains.poplibrary.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var createdAt: String,
    var updatedAt: String,
    var forksCount: Int,
    val forksUrl: String,
    var userId: String
)