package com.github.flickr

import android.app.Application
import com.github.flickr.dependency.BaseAppComponent
import com.github.flickr.dependency.DaggerAppComponent
import com.github.flickr.dependency.DownloadModule
import com.github.flickr.dependency.NetModule

class App : Application() {

    lateinit var baseComponent: BaseAppComponent

    override fun onCreate() {
        super.onCreate()
        baseComponent = DaggerAppComponent.builder()
                .application(this)
                .apiModule(NetModule())
                .downloadModule(DownloadModule())
                .build()
    }
}