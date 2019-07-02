package sky.movies.features.list.repository.service

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import sky.movies.features.list.repository.model.MoviesResponse
import sky.movies.network.URLs

interface MoviesService {

    @GET(URLs.MOVIES_LIST)
    fun getMovies() : Observable<Array<MoviesResponse>>
}