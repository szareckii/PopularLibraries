package com.szareckii.popularlibraries.mvp.model.image

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable

interface IImageCache {
    //contains
    //очистка - clear
    fun putImage(url: String, image: Bitmap?): Completable
    fun getBytes(url: String)
}