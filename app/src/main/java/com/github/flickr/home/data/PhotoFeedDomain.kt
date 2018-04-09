package com.github.flickr.home.data

data class PhotoFeedDomain(val entries: List<EntryDomain>) {

    data class EntryDomain(val title: String, val imageUrl: String?, val author: AuthorDomain?)

    data class AuthorDomain(val name: String, val photo: String)
}

