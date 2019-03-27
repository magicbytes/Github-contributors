package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution
import com.magicbytes.githubcontributor.network.User

class ContributorsPresenter(
        private val view: ContributorsView,
        private val model: ContributorsModel
) : ContributorsModel.Events {

    init {
        model.eventsListener = this
    }

    fun showContributors() {
        view.isLoadingVisible = true
        model.loadContributors()
    }

    override fun onLocationUserAvailable(user: User) {
        view.updateUserLocation(user)
    }

    override fun onContributionsAvailable(contributions: List<Contribution>) {
        view.isLoadingVisible = false
        val top25Contributors = contributions.map { Contributor(it.author.login, it.author.avatarUrl, it.total) }
                .sortedByDescending { it.numberCommits }
                .take(25)
        view.showContributors(top25Contributors)
        model.loadLocation(top25Contributors)
    }

    override fun onErrorLoadingContributions() {
        view.isLoadingVisible = false
        view.showNoData()
    }
}