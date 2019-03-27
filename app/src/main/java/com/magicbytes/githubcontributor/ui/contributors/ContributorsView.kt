package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.User

interface ContributorsView {
    var isLoadingVisible: Boolean

    fun showContributors(contributors: List<Contributor>)

    fun updateUserLocation(user: User)

    fun showNoData()
}