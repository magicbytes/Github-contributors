package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution

interface ContributorsModel {

    var eventsListener: Events?

    fun load()

    interface Events {
        fun onContributionsAvailable(contributions: List<Contribution>)

        fun onErrorLoadingContributions()
    }
}