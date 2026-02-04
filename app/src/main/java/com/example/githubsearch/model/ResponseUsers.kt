package com.example.githubsearch.model

import com.google.gson.annotations.SerializedName

data class ResponseUsers(
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,

    @SerializedName("items")
    val items: List<ItemUser>,

    @SerializedName("total_count")
    val total_count: Int
)