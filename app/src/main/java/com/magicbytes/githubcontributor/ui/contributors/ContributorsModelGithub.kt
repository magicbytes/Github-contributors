package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution
import com.magicbytes.githubcontributor.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributorsModelGithub : ContributorsModel {

    override var eventsListener: ContributorsModel.Events? = null

    override fun load() {
        NetworkManager.service().allContributions().enqueue(object : Callback<List<Contribution>> {
            override fun onFailure(call: Call<List<Contribution>>, t: Throwable) {
                eventsListener?.onErrorLoadingContributions()
            }

            override fun onResponse(call: Call<List<Contribution>>, response: Response<List<Contribution>>) {
                val posts = response.body()
                if (posts != null) {
                    eventsListener?.onContributionsAvailable(posts)
                } else {
                    eventsListener?.onErrorLoadingContributions()
                }
            }
        })
    }

}