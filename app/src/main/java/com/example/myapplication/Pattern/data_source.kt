package com.example.myapplication.Pattern

import com.google.gson.annotations.SerializedName

data class data_source(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)
