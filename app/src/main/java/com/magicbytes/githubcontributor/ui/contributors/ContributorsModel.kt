package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution
import com.magicbytes.githubcontributor.network.User

interface ContributorsModel {

    var eventsListener: Events?

    fun loadContributors()

    fun loadLocation(contributors: List<Contributor>)

    interface Events {
        fun onContributionsAvailable(contributions: List<Contribution>)

        fun onLocationUserAvailable(user: User)

        fun onErrorLoadingContributions()
    }
}