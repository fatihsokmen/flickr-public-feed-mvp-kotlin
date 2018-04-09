package com.github.flickr.home.data

import retrofit2.http.GET
import rx.Single

interface PhotoFeedApiService {

    @GET("feeds/photos_public.gne")
    fun getPhotos(): Single<PhotoFeedDTO>
}