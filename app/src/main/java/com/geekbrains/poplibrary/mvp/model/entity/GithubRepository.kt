package com.geekbrains.poplibrary.mvp.model.entity

import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose
import android.os.Parcelable

@Parcelize
data class GithubRepository(
    @Expose val id: String,
    @Expose val name: String?,
    @Expose val createdAt: String?,
    @Expose val userId: String,
    @Expose val forksCount: Int?,
    @Expose val forksUrl: String) : Parcelable