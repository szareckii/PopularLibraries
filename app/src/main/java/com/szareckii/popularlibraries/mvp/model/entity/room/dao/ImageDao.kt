package com.szareckii.popularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.szareckii.popularlibraries.mvp.model.entity.room.CashedImage

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: CashedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg images: CashedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<CashedImage>)

    @Update
    fun update(image: CashedImage)

    @Update
    fun update(vararg images: CashedImage)

    @Update
    fun update(images: List<CashedImage>)

    @Delete
    fun delete(image: CashedImage)

    @Delete
    fun delete(vararg images: CashedImage)

    @Delete
    fun delete(images: List<CashedImage>)

    @Query("SELECT * FROM CashedImage")
    fun getAll(): List<CashedImage>

    @Query("SELECT * FROM CashedImage WHERE avatarUrl = :avatarUrl LIMIT 1")
    fun findByUrl(avatarUrl: String): CashedImage?
}