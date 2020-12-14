package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomCachedImage (
    @PrimaryKey val url: String,
    val localPath: String
)