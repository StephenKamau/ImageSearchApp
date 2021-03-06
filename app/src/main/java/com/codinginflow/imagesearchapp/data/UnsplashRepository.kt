package com.codinginflow.imagesearchapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.codinginflow.imagesearchapp.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {
    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(
            pageSize = 40,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UnsplashPhotoPagingSource(unsplashApi, query) }
    ).liveData
}