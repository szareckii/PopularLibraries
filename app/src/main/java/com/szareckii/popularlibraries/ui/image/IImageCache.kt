package com.szareckii.popularlibraries.ui.image

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable

interface IImageCache {
    //containts
    //очистка  clear
    fun putImage(url: String, image: Bitmap?): Completable
    fun getBytes(url: String)
}