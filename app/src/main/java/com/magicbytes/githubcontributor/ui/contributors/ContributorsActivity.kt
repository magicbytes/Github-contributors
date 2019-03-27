package com.magicbytes.githubcontributor.ui.contributors

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.magicbytes.githubcontributor.R
import com.magicbytes.githubcontributor.network.Contribution
import com.magicbytes.githubcontributor.ui.contributors.adapter.ContributorsAdapter

class ContributorsActivity : AppCompatActivity(), ContributorsView {

    private var recyclerView: RecyclerView? = null
    private var refreshLayout: SwipeRefreshLayout? = null

    private lateinit var presenter: ContributorsPresenter

    override var isLoadingVisible: Boolean = false
        set(value) {
            field = value

            refreshLayout?.isRefreshing = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributors)

        refreshLayout = findViewById(R.id.refreshLayout)
        refreshLayout?.setColorSchemeResources(R.color.pink, R.color.indigo, R.color.lime)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        // An empty adapter is necessary for enabling the refresh layout
        recyclerView?.adapter = ContributorsAdapter(emptyList())

        refreshLayout?.setOnRefreshListener { presenter.showContributors() }

        presenter = ContributorsPresenter(this, ContributorsModelGithub())
        presenter.showContributors()
    }

    override fun showContributors(contributors: List<Contributor>) {
        recyclerView?.adapter = ContributorsAdapter(contributors)
    }

    override fun showNoData() {
        val view = recyclerView ?: return
        val snackbar = Snackbar.make(view, "There was an error, please try again", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Retry") { presenter.showContributors() }
        snackbar.show()
    }
}
