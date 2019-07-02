package sky.movies.network

import dagger.Reusable
import io.reactivex.Observable
import sky.movies.utils.baseResult
import javax.inject.Inject

@Reusable
open class NetworkRequest @Inject constructor(
    private val schedulers: SchedulersFacade
) {

    fun <T> request(retrofitRequest: Observable<T>): Observable<T> {
        return retrofitRequest
            .baseResult()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }
}