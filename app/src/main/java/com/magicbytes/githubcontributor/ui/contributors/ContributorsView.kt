package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution

interface ContributorsView {
    var isLoadingVisible: Boolean

    fun showContributors(contributors: List<Contributor>)

    fun showNoData()
}