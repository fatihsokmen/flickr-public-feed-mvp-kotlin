package com.github.flickr.home.data

import javax.inject.Inject

import rx.Single

class PhotoFeedApiInteractor @Inject constructor(
        private val apiService: PhotoFeedApiService,
        private val mapper: PhotoFeedDomainMapper) : PhotoFeedInteractor {

    override fun getPhotos(): Single<PhotoFeedDomain> {
        return apiService.getPhotos().map(mapper)
    }
}
