package com.github.flickr.home.adapter


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.github.flickr.home.data.PhotoFeedDomain
import com.github.flickr.home.viewholder.PhotoViewHolder
import com.github.flickr.home.viewholder.PhotoViewHolderFactory

import javax.inject.Inject

class PhotoFeedAdapter @Inject constructor(
        private val presenter: PhotoFeedAdapterContract.Presenter,
        private val viewHolderFactoryBuilder: PhotoViewHolderFactory.Builder)
    : RecyclerView.Adapter<PhotoViewHolder>(), PhotoFeedAdapterContract.View {

    init {
        presenter.setView(this)
    }

    fun bindData(entries: List<PhotoFeedDomain.EntryDomain>) {
        presenter.setEntries(entries)
    }

    override fun onDataChanged() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return viewHolderFactoryBuilder.parentView(parent).build().createViewHolder()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(presenter.get(position))
    }

    override fun getItemCount(): Int {
        return presenter.size
    }
}
