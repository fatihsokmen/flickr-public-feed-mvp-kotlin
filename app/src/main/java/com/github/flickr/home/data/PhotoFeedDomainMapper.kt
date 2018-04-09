package com.github.flickr.home.data


import com.github.flickr.home.data.PhotoFeedDTO.AuthorDTO
import com.github.flickr.home.data.PhotoFeedDTO.EntryDTO
import com.github.flickr.home.data.PhotoFeedDomain.AuthorDomain
import com.github.flickr.home.data.PhotoFeedDomain.EntryDomain
import rx.functions.Func1
import java.util.*
import javax.inject.Inject

class PhotoFeedDomainMapper @Inject constructor() : Func1<PhotoFeedDTO, PhotoFeedDomain> {

    override fun call(photoFeedDTO: PhotoFeedDTO): PhotoFeedDomain {
        val entryDomains = ArrayList<EntryDomain>()
        photoFeedDTO.entries?.forEach { dto ->
            entryDomains.add(mapEntry(dto))
        }
        return PhotoFeedDomain(entryDomains)
    }

    private fun mapEntry(entryDTO: EntryDTO): EntryDomain {
        return EntryDomain(
                entryDTO.title,
                mapUrl(entryDTO.links),
                entryDTO.author?.let {
                    mapAuthor(entryDTO.author)
                })
    }

    private fun mapAuthor(author: AuthorDTO?): AuthorDomain? {
        return author?.let { dto ->
            AuthorDomain(dto.name, dto.buddyicon)
        }
    }

    private fun mapUrl(links: List<PhotoFeedDTO.LinkDTO>?): String? {
        links?.forEach { linkDTO ->
            if (IMAGE_JPEG.equals(linkDTO.type!!, ignoreCase = true)) {
                return linkDTO.href
            }
        }
        return null
    }

    companion object {
        const val IMAGE_JPEG = "image/jpeg"
    }
}
