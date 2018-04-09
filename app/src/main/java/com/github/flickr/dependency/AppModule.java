package com.github.flickr.dependency;


import android.app.Application;
import android.content.Context;

import com.github.flickr.dependency.scheduler.Scheduler;
import com.github.flickr.dependency.scheduler.SchedulerImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module(includes = {
        AppModule.Bindings.class
})
class AppModule {

    @Module
    public interface Bindings {

        @Binds
        @Singleton
        Scheduler provideScheduler(SchedulerImpl impl);

        @Binds
        @Singleton
        Context provideApplicationContext(Application impl);
    }
}
