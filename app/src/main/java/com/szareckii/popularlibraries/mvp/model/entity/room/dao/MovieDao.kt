package com.szareckii.popularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.szareckii.popularlibraries.mvp.model.cache.image.room.RoomImageCache
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomIMDBMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: RoomIMDBMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomIMDBMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<RoomIMDBMovie>)

    @Update
    fun update(user: RoomIMDBMovie)

    @Update
    fun update(vararg movies: RoomIMDBMovie)

    @Update
    fun update(movies: List<RoomIMDBMovie>)

    @Delete
    fun delete(movie: RoomIMDBMovie)

    @Delete
    fun delete(vararg movies: RoomIMDBMovie)

    @Delete
    fun delete(movies: List<RoomIMDBMovie>)

    @Query("SELECT * FROM RoomIMDBMovie")
    fun getAll(): List<RoomIMDBMovie>

    @Query("SELECT * FROM RoomIMDBMovie WHERE title = :title LIMIT 1")
    fun findByLogin(title: String): RoomIMDBMovie?
}
