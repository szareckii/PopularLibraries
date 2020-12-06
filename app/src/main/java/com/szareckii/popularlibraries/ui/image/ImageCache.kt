package com.szareckii.popularlibraries.ui.image

import android.content.Context
import android.graphics.Bitmap
import com.szareckii.popularlibraries.mvp.model.entity.room.CashedImage
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomGithubUser
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class ImageCache(val context: Context, val db: Database): IImageCache {
//
//    override fun putImage(url: String, image: Bitmap?) {
//
//        //сохранене аватарок на устройстве
//        val imageName = url.substringAfterLast("/")
//        val imageFile = File(context.getExternalFilesDir(null), "$imageName.png")
//        val stream = FileOutputStream(imageFile)
//        image?.compress(Bitmap.CompressFormat.PNG, 100, stream)
//        stream.close()
//
//        //сохранене в БД ROOM
//
//    }

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

        val roomImage =
            CashedImage(url, путь к файлу)

        db.imageDao.insert(roomUsers)
        return true
    }

    //сохранене в БД ROOM

    override fun getBytes(url: String) {

    }
}