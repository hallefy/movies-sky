package sky.movies.factory

import sky.movies.features.list.repository.model.MoviesResponse
import sky.movies.features.list.viewmodel.model.MoviesData

fun getMoviesList(): MutableList<MoviesResponse> {
    val movies: MutableList<MoviesResponse> = ArrayList()
    movies.add(moviesDataFake())

    return movies
}

fun moviesDataFake() = MoviesResponse(
    mutableListOf("backdrop"),
    "cover",
    "1h 30min",
    "1",
    "Overview",
    "2019",
    "Title"
)