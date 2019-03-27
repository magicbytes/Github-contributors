package com.magicbytes.githubcontributor.network

import com.google.gson.annotations.SerializedName

data class ContributionWeek(
        @SerializedName("w")
        val weekStart: Long,
        @SerializedName("a")
        val numberAdditions: Int,
        @SerializedName("d")
        val numberDeletion: Int,
        @SerializedName("c")
        val numberCommits: Int
) {
}