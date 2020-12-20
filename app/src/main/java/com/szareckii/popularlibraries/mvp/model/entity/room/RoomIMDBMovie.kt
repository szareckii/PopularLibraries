package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
class RoomIMDBMovie(
    @PrimaryKey var id: String,
    var title: String,
    var image: String,
    var year: String,
    var imDbRating: String
)