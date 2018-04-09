package com.github.flickr.home.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

import com.github.flickr.home.data.PhotoFeedDomain

abstract class PhotoViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(entry: PhotoFeedDomain.EntryDomain)
}