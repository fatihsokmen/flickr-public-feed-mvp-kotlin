package com.github.flickr.home

import com.github.flickr.dependency.downloader.BitmapHolder
import com.github.flickr.dependency.downloader.ImageDownloader
import com.github.flickr.dependency.downloader.MediaStore
import com.github.flickr.dependency.scheduler.Scheduler
import com.github.flickr.home.data.PhotoFeedDomain
import com.github.flickr.home.data.PhotoFeedInteractor

import javax.inject.Inject

import rx.Completable
import rx.CompletableSubscriber
import rx.SingleSubscriber
import rx.Subscriber
import rx.Subscription
import rx.functions.Func1
import rx.subscriptions.CompositeSubscription

class HomeFragmentPresenter @Inject constructor(
        private val view: HomeFragmentContract.View,
        private val apiInteractor: PhotoFeedInteractor,
        private val imageDownloader: ImageDownloader,
        private val mediaStore: MediaStore,
        private val scheduler: Scheduler) : HomeFragmentContract.Presenter {

    private val subscriptions = CompositeSubscription()

    override fun init() {
        view.showProgress(true)

        subscriptions.clear()
        subscriptions.add(apiInteractor.getPhotos()
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribe(object : SingleSubscriber<PhotoFeedDomain>() {
                    override fun onSuccess(photoFeed: PhotoFeedDomain) {
                        view.bindData(photoFeed.entries)
                        view.showProgress(false)
                    }

                    override fun onError(error: Throwable) {
                        view.showProgress(false)
                        view.showError("Error loading photo feed")
                    }
                }))
    }

    override fun sharePhoto(photoUrl: String) {
        view.showProgress(true)

        subscriptions.clear()
        subscriptions.add(imageDownloader.downloadBitmap(photoUrl)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribe(object : Subscriber<BitmapHolder>() {
                    override fun onNext(bitmapHolder: BitmapHolder) {
                        view.showMessage("Shared successfully")
                    }

                    override fun onError(error: Throwable) {
                        view.showError("Can't not download photo")
                    }

                    override fun onCompleted() {
                        view.showProgress(false)
                    }
                }))
    }

    override fun savePhoto(photoUrl: String) {
        view.showProgress(true)

        imageDownloader.downloadBitmap(photoUrl)
                .flatMapCompletable {
                    bitmapHolder -> mediaStore.saveBitmap(bitmapHolder.bitmap)
                }
                .toCompletable()
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribe(object : CompletableSubscriber {
                    override fun onCompleted() {
                        view.showProgress(false)
                        view.showMessage("Saved to gallery successfully")
                    }

                    override fun onError(e: Throwable) {
                        view.showProgress(false)
                        view.showError("Failed to save to gallery")
                    }

                    override fun onSubscribe(d: Subscription) {}
                })
    }

    override fun clearSubscriptions() {
        subscriptions.clear()
    }
}
