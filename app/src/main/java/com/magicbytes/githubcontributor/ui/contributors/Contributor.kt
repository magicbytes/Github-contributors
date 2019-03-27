package com.magicbytes.githubcontributor.ui.contributors

class Contributor(
        val userName: String,
        val avatarUrl: String,
        val numberCommits: Int,
        val location: String = ""
) {
    val hasLocation: Boolean
        get() = location.isNotEmpty()
}