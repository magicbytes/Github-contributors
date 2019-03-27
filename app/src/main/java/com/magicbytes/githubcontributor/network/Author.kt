package com.magicbytes.githubcontributor.network

import com.google.gson.annotations.SerializedName

data class Author(
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
) {
}