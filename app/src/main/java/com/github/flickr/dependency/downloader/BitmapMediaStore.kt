package com.github.flickr.dependency.downloader

import android.content.Context
import android.graphics.Bitmap
import rx.Completable
import java.util.*
import javax.inject.Inject

class BitmapMediaStore @Inject constructor(private val context: Context) : MediaStore {


    override fun saveBitmap(bitmap: Bitmap): Completable {
        return Completable.fromAction {
            android.provider.MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    bitmap,
                    UUID.randomUUID().toString(), null)
                    ?: throw SecurityException("Can't save to gallery")
        }
    }

}
