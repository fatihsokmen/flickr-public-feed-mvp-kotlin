package com.github.flickr.dependency

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.github.flickr.dependency.downloader.BitmapMediaStore
import com.github.flickr.dependency.downloader.GlideImageDownloader
import com.github.flickr.dependency.downloader.ImageDownloader
import com.github.flickr.dependency.downloader.MediaStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
        includes = [DownloadModule.Bindings::class]
)
class DownloadModule {

    @Provides
    @Singleton
    fun provideGlide(context: Context): RequestManager {
        return Glide.with(context)
    }

    @Module
    interface Bindings {
        @Binds
        @Singleton
        fun provideFragmentPresenter(impl: GlideImageDownloader): ImageDownloader

        @Binds
        @Singleton
        fun provideMediaStorePresenter(impl: BitmapMediaStore): MediaStore
    }

}