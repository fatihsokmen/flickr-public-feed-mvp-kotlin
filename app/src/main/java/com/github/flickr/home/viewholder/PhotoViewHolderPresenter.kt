package com.github.flickr.home.viewholder

import com.github.flickr.home.data.PhotoFeedDomain
import javax.inject.Inject

class PhotoViewHolderPresenter @Inject constructor(
        private val interactions: PhotoViewHolderContract.Interactions)
    : PhotoViewHolderContract.Presenter {

    private lateinit var view: PhotoViewHolderContract.View

    private var entry: PhotoFeedDomain.EntryDomain? = null

    override fun setView(view: PhotoViewHolderContract.View) {
        this.view = view
    }

    override fun bindData(entry: PhotoFeedDomain.EntryDomain) {
        this.entry = entry
        view.apply {
            setAuthorPhoto(entry.author?.photo)
            setImageUrl(entry.imageUrl)
            setAuthorName(entry.author?.name)
            setTitle(entry.title)
        }
    }

    override fun onSavePhotoClicked() {
        entry?.imageUrl?.let { imageUrl ->
            interactions.onSavePhoto(imageUrl)
        }

    }

    override fun onSharePhotoClicked() {
        entry?.imageUrl?.let { imageUrl ->
            interactions.onSharePhoto(imageUrl)
        }
    }
}
