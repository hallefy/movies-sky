package sky.movies.features.list.view

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movies_list.*
import sky.movies.R
import sky.movies.features.detail.view.DetailDialog
import sky.movies.features.list.view.adapter.MoviesAdapter
import sky.movies.features.list.viewmodel.MoviesViewModel
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.AutoFitGridLayoutManager
import sky.movies.utils.ViewModelFactory
import sky.movies.utils.ViewState
import sky.movies.utils.gone
import sky.movies.utils.observe
import sky.movies.utils.stopRefreshing
import sky.movies.utils.visible
import javax.inject.Inject

class MoviesListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
        addObservers()
        setupListener()
    }

    private fun setupListener() {
        swipRefresh.setOnRefreshListener {
            viewModel.getMovies()
        }

        swipRefreshError.setOnRefreshListener {
            viewModel.getMovies()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
    }

    private fun addObservers() {
        observe(viewModel.viewState) {
            when(it) {
                ViewState.ERROR -> {
                    swipRefreshError.visible()
                    swipRefresh.stopRefreshing()
                    swipRefreshError.stopRefreshing()
                    recyclerView.gone()
                    animationView.gone()
                }
                ViewState.LOADING -> {
                    swipRefreshError.gone()
                    swipRefreshError.stopRefreshing()
                    swipRefresh.stopRefreshing()
                    recyclerView.gone()
                    animationView.playAnimation()
                }
                ViewState.SUCCESS -> {
                    swipRefreshError.gone()
                    animationView.gone()
                    animationView.clearAnimation()
                    swipRefresh.stopRefreshing()
                    swipRefreshError.stopRefreshing()
                    recyclerView.visible()
                }
            }
        }

        observe(viewModel.data) {
            addMovies(it)
        }
    }

    @VisibleForTesting(otherwise = Context.MODE_PRIVATE)
    fun addMovies(moviesData: List<MoviesData>) {
        recyclerView.apply {
            layoutManager = AutoFitGridLayoutManager(this@MoviesListActivity, 500)
            adapter = MoviesAdapter(moviesData) {
                startDetails(it)
            }
        }
    }

    private fun startDetails(moviesData: MoviesData) {
        DetailDialog(this, moviesData).show()
    }
}
