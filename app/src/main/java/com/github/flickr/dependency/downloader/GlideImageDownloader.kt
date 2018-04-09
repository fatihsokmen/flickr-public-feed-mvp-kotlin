package com.github.flickr.dependency.downloader


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import rx.Observable
import rx.subjects.AsyncSubject
import javax.inject.Inject

class GlideImageDownloader @Inject constructor(private val glide: RequestManager) : ImageDownloader {

    override fun downloadBitmap(url: String): Observable<BitmapHolder> {
        val notifier = AsyncSubject.create<BitmapHolder>()

        glide.asBitmap().load(url).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap,
                                         transition: Transition<in Bitmap>?) {
                notifier.onNext(BitmapHolderImpl(resource))
                notifier.onCompleted()
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                notifier.onError(Throwable("Can't load image"))
                notifier.onCompleted()
            }
        })

        return notifier
    }
}
