package com.github.flickr.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.github.flickr.R
import com.github.flickr.home.data.PhotoFeedDomain
import javax.inject.Inject

class PhotoViewHolderView @Inject constructor(itemView: View, private val presenter: PhotoViewHolderContract.Presenter)
    : PhotoViewHolder(itemView), PhotoViewHolderContract.View {

    @BindView(R.id.user_name)
    lateinit var userNameView: TextView

    @BindView(R.id.user_photo)
    lateinit var userPhotoView: ImageView

    @BindView(R.id.image)
    lateinit var imageView: ImageView

    @BindView(R.id.title)
    lateinit var titleView: TextView

    init {
        this.presenter.setView(this)
        ButterKnife.bind(this, itemView)
    }

    override fun bind(entry: PhotoFeedDomain.EntryDomain) {
        presenter.bindData(entry)
    }

    override fun setTitle(title: String?) {
        titleView.text = title
    }

    override fun setAuthorName(name: String?) {
        userNameView.text = name
    }

    override fun setAuthorPhoto(photo: String?) {
        Glide.with(itemView).load(photo).into(userPhotoView)
    }

    override fun setImageUrl(imageUrl: String?) {
        Glide.with(itemView).load(imageUrl).into(imageView)
    }

    @OnClick(R.id.save_button)
    fun onSavePhotoClicked() {
        presenter.onSavePhotoClicked()
    }

    @OnClick(R.id.share_button)
    fun onSharePhotoClicked() {
        presenter.onSharePhotoClicked()
    }
}