package com.github.flickr.home


import javax.inject.Inject

class HomeFragmentInteractions @Inject constructor(
        private val homePresenter: HomeFragmentContract.Presenter)
    : HomeFragmentContract.Interactions {

    override fun onSharePhoto(photoUrl: String) {
        homePresenter.sharePhoto(photoUrl)
    }

    override fun onSavePhoto(photoUrl: String) {
        homePresenter.savePhoto(photoUrl)
    }
}
