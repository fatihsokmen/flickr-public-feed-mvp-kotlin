package com.github.flickr.home.adapter

import android.support.annotation.IntRange

import com.github.flickr.home.data.PhotoFeedDomain

class PhotoFeedAdapterContract {

    interface View {

        fun onDataChanged()
    }

    interface Presenter {

        @get:IntRange(from = 0)
        val size: Int

        fun setView(view: View)

        fun setEntries(entries: List<PhotoFeedDomain.EntryDomain>)

        fun get(position: Int): PhotoFeedDomain.EntryDomain
    }
}
