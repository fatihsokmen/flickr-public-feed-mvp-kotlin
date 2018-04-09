package com.github.flickr.home

import com.github.flickr.dependency.BaseAppComponent
import com.github.flickr.dependency.scope.FragmentViewScope
import dagger.Component

@Component(
        dependencies = [BaseAppComponent::class],
        modules = [HomeFragmentModule::class]
)
@FragmentViewScope
interface HomeFragmentComponent {

    fun inject(fragment: HomeFragment)
}