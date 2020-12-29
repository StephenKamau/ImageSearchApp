package com.codinginflow.imagesearchapp.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.codinginflow.imagesearchapp.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val currentQuery = savedStateHandle.getLiveData(
        CURRENT_SEARCH_QUERY,
        DEFAULT_SEARCH_QUERY
    )
    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_SEARCH_QUERY = "current_query"
        private const val DEFAULT_SEARCH_QUERY = "cats"
    }
}