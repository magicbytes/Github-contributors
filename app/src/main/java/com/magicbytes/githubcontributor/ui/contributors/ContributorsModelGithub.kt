package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Contribution
import com.magicbytes.githubcontributor.network.NetworkManager
import com.magicbytes.githubcontributor.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributorsModelGithub : ContributorsModel {
    override var eventsListener: ContributorsModel.Events? = null

    override fun loadLocation(contributors: List<Contributor>) {
        contributors.forEach {
            NetworkManager.service().userInfo(it.userName).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    // TODO log a warning
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body() ?: return
                    if (user.location != null) {
                        eventsListener?.onLocationUserAvailable(user)
                    }
                }
            })
        }
    }

    override fun loadContributors() {
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