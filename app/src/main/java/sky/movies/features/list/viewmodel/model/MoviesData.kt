package sky.movies.features.list.viewmodel.model

data class MoviesData(
    val backdrops: MutableList<String>,
    val cover: String,
    val duration: String,
    val id: String,
    val overview: String,
    val release_year: String,
    val title: String
)