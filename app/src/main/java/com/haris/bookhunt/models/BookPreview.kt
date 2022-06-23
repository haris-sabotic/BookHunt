package com.haris.bookhunt.models

import com.google.gson.annotations.SerializedName

data class BookPreview(
    @SerializedName("key")
    val key: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("cover_i", alternate = ["cover_id"])
    val coverId: Int,
)
