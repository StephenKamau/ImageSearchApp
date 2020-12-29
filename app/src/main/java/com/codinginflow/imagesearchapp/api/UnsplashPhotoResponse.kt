package com.codinginflow.imagesearchapp.api

import com.codinginflow.imagesearchapp.data.UnsplashPhoto

data class UnsplashPhotoResponse(
    val results: List<UnsplashPhoto>
)