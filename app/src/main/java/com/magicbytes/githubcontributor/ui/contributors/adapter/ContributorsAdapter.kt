package com.magicbytes.githubcontributor.ui.contributors.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.magicbytes.githubcontributor.R
import com.magicbytes.githubcontributor.network.User
import com.magicbytes.githubcontributor.ui.contributors.Contributor


class ContributorsAdapter(private val contributors: List<Contributor>) : RecyclerView.Adapter<ContributorViewHolder>() {

    // Allows to remember the last item shown on screen. Animation purpose
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_contributor, parent, false)
        return ContributorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.show(contributors[position])
        setAnimation(holder.itemView, position);
    }

    override fun getItemCount(): Int {
        return contributors.size
    }

    fun updateLocation(user: User) {
        val indexUser = contributors.indexOfFirst { it.userName == user.login }
        if (indexUser != -1) {
            contributors[indexUser].location = user.location ?: ""
            notifyItemChanged(indexUser)
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}