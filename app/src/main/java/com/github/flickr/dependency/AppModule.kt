package com.github.flickr.dependency


import android.app.Application
import android.content.Context
import com.github.flickr.dependency.scheduler.Scheduler
import com.github.flickr.dependency.scheduler.SchedulerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
        includes = [AppModule.Bindings::class]
)
class AppModule {

    @Module
    interface Bindings {

        @Binds
        @Singleton
        fun provideScheduler(impl: SchedulerImpl): Scheduler

        @Binds
        @Singleton
        fun provideApplicationContext(impl: Application): Context
    }
}
