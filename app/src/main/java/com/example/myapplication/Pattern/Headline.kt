package com.example.myapplication.Pattern

import com.google.gson.annotations.SerializedName

data class Headline(
    @SerializedName("status") val status: String,
    @SerializedName("totalResult") val totalResults: Int,
    @SerializedName("articles") val articles: List<Articles>
)
