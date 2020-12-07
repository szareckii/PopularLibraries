package com.szareckii.popularlibraries.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.szareckii.popularlibraries.mvp.model.image.IImageCache
import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus

class GlideImageLoader(val imageCache: IImageCache, private val networkStatus: INetworkStatus): IImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {

//        if (isOnline) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
//            .load(byteArray)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageCache.putImage(url, resource)
                    return false
                }
            })
            .into(container)

    }
}