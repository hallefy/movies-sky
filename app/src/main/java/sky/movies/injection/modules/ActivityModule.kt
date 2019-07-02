package sky.movies.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sky.movies.features.list.view.MoviesListActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesListActivity() : MoviesListActivity
}