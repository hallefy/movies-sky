package sky.movies.features.list.repository.model

data class MoviesResponse(
    val backdrops_url: MutableList<String>,
    val cover_url: String,
    val duration: String,
    val id: String,
    val overview: String,
    val release_year: String,
    val title: String
)

