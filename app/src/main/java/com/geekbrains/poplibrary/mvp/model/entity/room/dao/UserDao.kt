package com.geekbrains.poplibrary.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.geekbrains.poplibrary.mvp.model.entity.room.RoomGithubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUser?
}