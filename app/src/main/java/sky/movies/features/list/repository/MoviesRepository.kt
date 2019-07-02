package sky.movies.features.list.repository

import sky.movies.features.list.repository.mapper.toMoviesData
import sky.movies.features.list.repository.service.MoviesService
import sky.movies.features.list.viewmodel.model.MoviesData
import sky.movies.network.NetworkRequest
import sky.movies.utils.ResourceResponse
import sky.movies.utils.toResourceResponse
import javax.inject.Inject

interface MoviesRepository {
    fun getMovies(): ResourceResponse<List<MoviesData>>
}

class MoviesRepositoryImpl @Inject constructor(
    private val worker: NetworkRequest,
    private val service: MoviesService
) : MoviesRepository {

    override fun getMovies(): ResourceResponse<List<MoviesData>> {
        return worker.request(service.getMovies())
            .map {
                it.map { moviesItem -> moviesItem.toMoviesData() }
            }
            .toResourceResponse()
    }
}