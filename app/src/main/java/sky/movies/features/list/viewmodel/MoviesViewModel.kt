package sky.movies.features.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import sky.movies.features.list.repository.MoviesRepository
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.utils.ResourceResponse
import sky.movies.utils.ViewState
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val moviesRequest = MutableLiveData<ResourceResponse<List<MoviesData>>>()

    val data: LiveData<List<MoviesData>> = switchMap(moviesRequest) { it.data }
    val viewState: LiveData<ViewState> = switchMap(moviesRequest) { it.state }

    fun getMovies() {
        moviesRequest.value = moviesRepository.getMovies()
    }
}