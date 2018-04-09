package com.github.flickr.dependency

import android.app.Application
import android.content.Context
import com.github.flickr.dependency.downloader.ImageDownloader
import com.github.flickr.dependency.downloader.MediaStore
import com.github.flickr.dependency.scheduler.Scheduler
import dagger.BindsInstance
import retrofit2.Retrofit

interface BaseAppComponent {

    fun provideApplicationContext(): Context

    fun provideScheduler(): Scheduler

    fun provideRetrofit(): Retrofit

    fun provideImageDownloader(): ImageDownloader

    fun provideMediaStore(): MediaStore

    interface Builder<C : BaseAppComponent, B : Builder<C, B>> {

        @BindsInstance
        fun application(application: Application): B

        @BindsInstance
        fun apiModule(apiModule: NetModule): B

        @BindsInstance
        fun downloadModule(apiModule: DownloadModule): B

        fun build(): C
    }
}