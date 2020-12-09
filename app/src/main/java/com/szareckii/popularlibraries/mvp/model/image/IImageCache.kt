package com.szareckii.popularlibraries.mvp.model.image

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IImageCache {
    //containts
    //очистка  clear
    fun putImage(url: String, image: Bitmap?): Completable
    fun getBytes(url: String)
}