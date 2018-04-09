package com.github.flickr.home

import com.github.flickr.home.data.PhotoFeedDomain
import com.github.flickr.home.viewholder.PhotoViewHolderContract

interface HomeFragmentContract {

    interface View {

        fun showProgress(show: Boolean)

        fun showError(message: String)

        fun showMessage(message: String)

        fun bindData(entries: List<PhotoFeedDomain.EntryDomain>)
    }

    interface Presenter {

        fun init()

        fun clearSubscriptions()

        fun sharePhoto(photoUrl: String)

        fun savePhoto(photoUrl: String)
    }

    interface Interactions : PhotoViewHolderContract.Interactions
}
