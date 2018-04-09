package com.github.flickr.home.viewholder


import android.view.ViewGroup

import com.github.flickr.dependency.scope.ViewHolderScope
import com.github.flickr.home.viewholder.PhotoViewHolderModule.PhotoViewHolderLayoutModule

import dagger.BindsInstance
import dagger.Component

@Component(
        modules = [PhotoViewHolderModule::class]
)
@ViewHolderScope
interface PhotoViewHolderFactory {

    fun createViewHolder(): PhotoViewHolder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun parentView(parentView: ViewGroup): Builder

        @BindsInstance
        fun viewHolderInteractions(interactions: PhotoViewHolderContract.Interactions): Builder

        fun layoutModule(layoutModule: PhotoViewHolderLayoutModule): Builder

        fun build(): PhotoViewHolderFactory
    }
}