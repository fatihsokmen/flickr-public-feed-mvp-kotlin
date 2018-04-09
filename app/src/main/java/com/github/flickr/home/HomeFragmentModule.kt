package com.github.flickr.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView

import com.github.flickr.R
import com.github.flickr.dependency.scope.FragmentViewScope
import com.github.flickr.home.adapter.PhotoFeedAdapter
import com.github.flickr.home.adapter.PhotoFeedAdapterContract
import com.github.flickr.home.adapter.PhotoFeedAdapterPresenter
import com.github.flickr.home.data.PhotoFeedApiInteractor
import com.github.flickr.home.data.PhotoFeedApiService
import com.github.flickr.home.data.PhotoFeedInteractor
import com.github.flickr.home.viewholder.DaggerPhotoViewHolderFactory
import com.github.flickr.home.viewholder.PhotoViewHolderFactory
import com.github.flickr.home.viewholder.PhotoViewHolderModule.PhotoViewHolderLayoutModule

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(
        includes = [HomeFragmentModule.Bindings::class]
)
internal class HomeFragmentModule(private val view: HomeFragmentContract.View) {

    @Provides
    @FragmentViewScope
    fun provideView(): HomeFragmentContract.View {
        return view
    }

    @Provides
    @FragmentViewScope
    fun provideApiService(retrofit: Retrofit): PhotoFeedApiService {
        return retrofit.create(PhotoFeedApiService::class.java)
    }

    @Provides
    @FragmentViewScope
    fun provideViewHolderFactory(
            interactions: HomeFragmentContract.Interactions): PhotoViewHolderFactory.Builder {
        return DaggerPhotoViewHolderFactory
                .builder()
                .viewHolderInteractions(interactions)
                .layoutModule(PhotoViewHolderLayoutModule(R.layout.view_feed_item))
    }

    @Provides
    @FragmentViewScope
    fun provideItemDecoration(context: Context): RecyclerView.ItemDecoration {
        val itemDecoration = DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        val drawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.elevated_list_divider)
        drawable?.let {
            itemDecoration.setDrawable(drawable)
        }
        return itemDecoration
    }


    @Module
    interface Bindings {

        @Binds
        @FragmentViewScope
        fun provideFragmentPresenter(
                presenter: HomeFragmentPresenter): HomeFragmentContract.Presenter

        @Binds
        @FragmentViewScope
        fun provideAdapterView(
                adapter: PhotoFeedAdapter): PhotoFeedAdapterContract.View

        @Binds
        @FragmentViewScope
        fun provideAdapterPresenter(
                presenter: PhotoFeedAdapterPresenter): PhotoFeedAdapterContract.Presenter

        @Binds
        @FragmentViewScope
        fun provideApiInteractor(interactor: PhotoFeedApiInteractor): PhotoFeedInteractor

        @Binds
        @FragmentViewScope
        fun provideInteractions(interactions: HomeFragmentInteractions): HomeFragmentContract.Interactions
    }

}
