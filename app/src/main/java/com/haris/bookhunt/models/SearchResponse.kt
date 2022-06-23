package com.haris.bookhunt.models

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("docs")
    val books: List<BookPreview>,
)
