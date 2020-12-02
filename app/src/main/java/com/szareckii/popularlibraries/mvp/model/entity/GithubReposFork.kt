package com.szareckii.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubReposFork(
    @Expose val fullName: String
): Parcelable