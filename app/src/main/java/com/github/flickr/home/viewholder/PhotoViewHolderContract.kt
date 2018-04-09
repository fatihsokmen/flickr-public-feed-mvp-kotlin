package com.github.flickr.home.viewholder

import com.github.flickr.home.data.PhotoFeedDomain

interface PhotoViewHolderContract {

    interface View {

        fun setAuthorPhoto(photo: String?)

        fun setAuthorName(name: String?)

        fun setImageUrl(imageUrl: String?)

        fun setTitle(title: String?)
    }

    interface Presenter {

        fun setView(view: View)

        fun bindData(entry: PhotoFeedDomain.EntryDomain)

        fun onSharePhotoClicked()

        fun onSavePhotoClicked()
    }

    interface Interactions {

        fun onSharePhoto(photoUrl: String)

        fun onSavePhoto(photoUrl: String)
    }
}
