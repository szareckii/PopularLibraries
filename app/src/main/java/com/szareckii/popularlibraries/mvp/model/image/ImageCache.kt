package com.szareckii.popularlibraries.mvp.model.image

import android.content.Context
import android.graphics.Bitmap
import com.szareckii.popularlibraries.mvp.model.entity.room.CashedImage
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class ImageCache(val context: Context, val db: Database): IImageCache {

    override fun putImage(url: String, image: Bitmap?): Completable = Completable.create { emitter ->

        imageToFile(url, image).let {
            if (it) {
                emitter.onComplete()
            } else {
                emitter.onError(RuntimeException("Error"))
                return@create
            }
        }
    }.subscribeOn(Schedulers.io())

    private fun imageToFile(url: String, image: Bitmap?): Boolean {
        //сохранене аватарок на устройстве
        val imageName = url.substringAfterLast("/")
        val imageFile = File(context.getExternalFilesDir(null), "$imageName.png")
        val stream = FileOutputStream(imageFile)
        image?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()

        //сохранение в БД ROOM
        println("!!!!!!!!!!!111111111111111$url")
        println("!!!!!!!!!!!222222222222222" +  imageFile.absolutePath)
        val roomImage = CashedImage(url, imageFile.absolutePath)
        db.imageDao.insert(roomImage)
        return true
    }

    override fun getBytes(url: String) {

    }
}