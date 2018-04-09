package com.github.flickr.home.adapter

import android.support.annotation.IntRange
import com.github.flickr.home.data.PhotoFeedDomain
import javax.inject.Inject

class PhotoFeedAdapterPresenter @Inject constructor()
    : PhotoFeedAdapterContract.Presenter {

    private var entries: List<PhotoFeedDomain.EntryDomain> = emptyList()

    private lateinit var view: PhotoFeedAdapterContract.View

    override val size: Int
        @IntRange(from = 0)
        get() = entries.size

    override fun setView(view: PhotoFeedAdapterContract.View) {
        this.view = view
    }

    override fun setEntries(entries: List<PhotoFeedDomain.EntryDomain>) {
        this.entries = entries
        view.onDataChanged()
    }

    override operator fun get(position: Int): PhotoFeedDomain.EntryDomain {
        return entries[position]
    }
}
