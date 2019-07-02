package sky.movies.features.list.repository.mapper

import sky.movies.features.list.repository.model.MoviesResponse
import sky.movies.features.list.viewmodel.model.MoviesData

fun MoviesResponse.toMoviesData(): MoviesData {
    return MoviesData(
        backdrops_url,
        cover_url,
        "Duração: $duration",
        id,
        overview,
        "Ano: $release_year",
        title
    )
}