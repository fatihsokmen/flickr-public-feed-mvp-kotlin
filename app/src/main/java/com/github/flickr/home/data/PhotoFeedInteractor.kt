package com.github.flickr.home.data

import rx.Single

interface PhotoFeedInteractor {

    fun getPhotos(): Single<PhotoFeedDomain>
}