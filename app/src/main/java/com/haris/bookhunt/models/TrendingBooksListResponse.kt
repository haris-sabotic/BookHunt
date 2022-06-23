package com.haris.bookhunt.models

import com.google.gson.annotations.SerializedName

data class TrendingBooksListResponse(
    @SerializedName("query")
    val query: String,

    @SerializedName("works")
    val books: List<BookPreview>,
)
