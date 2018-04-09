package com.github.flickr.dependency.downloader

import rx.Observable


interface ImageDownloader {

    fun downloadBitmap(url: String): Observable<BitmapHolder>
}
