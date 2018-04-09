package com.github.flickr.dependency

import dagger.Component
import javax.inject.Singleton

@Component(
        modules = [AppModule::class, NetModule::class, DownloadModule::class]
)
@Singleton
interface AppComponent : BaseAppComponent {

    @Component.Builder
    interface Builder : BaseAppComponent.Builder<AppComponent, Builder>
}
