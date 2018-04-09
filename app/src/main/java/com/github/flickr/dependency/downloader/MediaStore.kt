package com.github.flickr.dependency.downloader

import android.graphics.Bitmap
import rx.Completable

interface MediaStore {

    fun saveBitmap(bitmap: Bitmap): Completable
}