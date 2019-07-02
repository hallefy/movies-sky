package sky.movies.injection.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import sky.movies.features.list.repository.MoviesRepository
import sky.movies.features.list.repository.MoviesRepositoryImpl
import sky.movies.network.SchedulersFacade
import sky.movies.network.SchedulersFacadeImpl

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun providerApplicationContext(application: Application): Context

    @Binds
    abstract fun provideGson(gson: Gson): Gson

    @Binds
    abstract fun provideSchedulers(schedulers: SchedulersFacadeImpl): SchedulersFacade

    @Binds
    abstract fun provideRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}