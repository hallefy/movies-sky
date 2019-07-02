package sky.movies.injection.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import sky.movies.features.list.repository.service.MoviesService
import javax.inject.Named

@Module
class RequestModule {

    @Provides
    @Reusable
    fun provideMoviesService(
        @Named("RetrofitApi") retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}