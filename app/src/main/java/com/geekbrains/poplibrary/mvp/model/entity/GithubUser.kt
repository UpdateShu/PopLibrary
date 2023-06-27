package com.geekbrains.poplibrary.mvp.model.entity

import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose
import android.os.Parcelable

@Parcelize
data class GithubUser(
    @Expose val id: String,
    @Expose val login: String,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
) : Parcelable