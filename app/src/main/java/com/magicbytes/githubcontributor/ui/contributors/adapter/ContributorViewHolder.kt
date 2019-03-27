package com.magicbytes.githubcontributor.ui.contributors.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.magicbytes.githubcontributor.R
import com.magicbytes.githubcontributor.ui.contributors.Contributor
import com.magicbytes.githubcontributor.ui.geo.MapsActivity
import com.squareup.picasso.Picasso

class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val commitsCountTextView: TextView = itemView.findViewById(R.id.commitsCount)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val locationButton: Button = itemView.findViewById(R.id.locationButton)

    // TODO improve this by moving click logic into adapter or any other hierarchy parent
    private var currentContributor: Contributor? = null

    init {
        locationButton.setOnClickListener { showDetailsPost() }
    }

    fun show(contributor: Contributor) {
        currentContributor = contributor

        Picasso.get().load(Uri.parse(contributor.avatarUrl)).into(imageView)
        titleTextView.text = contributor.userName
        commitsCountTextView.text = contributor.numberCommits.toString()

        locationButton.visibility = if (contributor.hasLocation) View.VISIBLE else View.GONE
    }

    private fun showDetailsPost() {
        val contributor = currentContributor ?: return
        MapsActivity.start(titleTextView.context, contributor.location)
    }
}