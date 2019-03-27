package com.magicbytes.githubcontributor.ui.contributors

import com.magicbytes.githubcontributor.network.Author
import com.magicbytes.githubcontributor.network.Contribution
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ContributorsPresenterTest {
    private lateinit var presenter: ContributorsPresenter
    private lateinit var view: ContributorsView
    private lateinit var model: ContributorsModel

    @Before
    fun setUp() {
        view = Mockito.mock(ContributorsView::class.java)
        model = Mockito.mock(ContributorsModel::class.java)
        presenter = ContributorsPresenter(view, model)
    }

    @Test
    fun showPosts_ViewInitialisation() {
        presenter.showContributors()

        Mockito.verify(view).isLoadingVisible = true
        // TODO this is actually not belonging to this method, should be in apart method
        Mockito.verify(model, Mockito.times(1)).loadContributors()
    }

    @Test
    fun showPosts_NetworkError() {
        Mockito.`when`(model.loadContributors()).then { presenter.onErrorLoadingContributions() }
        presenter.showContributors()

        Mockito.verify(view).isLoadingVisible = false
        Mockito.verify(view, Mockito.times(1)).showNoData()
    }

    @Test
    fun showPosts_PostsAvailable() {
        val post = Contribution(1, Author("jack", ""), emptyList())
        Mockito.`when`(model.loadContributors()).then { presenter.onContributionsAvailable(listOf(post)) }
        presenter.showContributors()

        val contributor = Contributor(post.author.login, post.author.avatarUrl, post.total)

        Mockito.verify(view).isLoadingVisible = false
        Mockito.verify(view).showContributors(listOf(contributor))
    }
}