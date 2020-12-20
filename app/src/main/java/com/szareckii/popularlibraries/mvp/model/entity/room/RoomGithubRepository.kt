package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    foreignKeys = [ForeignKey(
        entity = RoomIMDBMovie::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGithubRepository(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: String,
    var fullName: String,
    var userId: String
)
