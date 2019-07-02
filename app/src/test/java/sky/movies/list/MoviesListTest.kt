package sky.movies.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import sky.movies.factory.getMoviesList
import sky.movies.features.list.repository.MoviesRepositoryImpl
import sky.movies.features.list.repository.mapper.toMoviesData
import sky.movies.features.list.viewmodel.MoviesViewModel
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.ViewState
import sky.movies.utils.asMutable
import sky.movies.utils.mockResourceResponse
import sky.movies.utils.test

@RunWith(JUnit4::class)
class MoviesListTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val repository = mockk<MoviesRepositoryImpl>()
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun `when request is success should call data with SUCCESS`() {
        shouldReturnSuccess()

        viewModel.getMovies()

        verify(exactly = 1) {
            repository.getMovies()
        }

        viewModel.data.test()
            .assertValue(getMoviesList().map { it.toMoviesData() }, 1)

        viewModel.viewState.test()
            .assertValue(ViewState.SUCCESS, 1)
    }

    @Test
    fun `when request fail should call with ERROR`() {
        shouldReturnState(ViewState.ERROR)

        viewModel.getMovies()

        verify(exactly = 1) {
            repository.getMovies()
        }

        viewModel.data.test()
            .assertInvoked(0)

        viewModel.viewState.test()
            .assertValue(ViewState.ERROR, 1)
    }

    @Test
    fun `when request fail should call with LOADING`() {
        shouldReturnState(ViewState.LOADING)

        viewModel.getMovies()

        verify(exactly = 1) {
            repository.getMovies()
        }

        viewModel.data.test()
            .assertInvoked(0)

        viewModel.viewState.test()
            .assertValue(ViewState.LOADING, 1)
    }

    private fun shouldReturnSuccess() {
        val resourceResponse = mockResourceResponse<List<MoviesData>>().apply {
            state.asMutable.postValue(ViewState.SUCCESS)
            data.asMutable.postValue(getMoviesList().map { it.toMoviesData() })
        }

        every {
            repository.getMovies()
        }.returns(resourceResponse)
    }

    private fun shouldReturnState(viewState: ViewState) {
        val resourceResponse = mockResourceResponse<List<MoviesData>>().apply {
            state.asMutable.postValue(viewState)
        }

        every {
            repository.getMovies()
        }.returns(resourceResponse)
    }
}