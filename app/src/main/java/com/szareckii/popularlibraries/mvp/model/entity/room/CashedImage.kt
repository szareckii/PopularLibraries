package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CashedImage (
    @PrimaryKey var avatarUrl: String,
    var avatarPath: String
)