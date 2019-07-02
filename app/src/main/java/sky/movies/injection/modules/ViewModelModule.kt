package sky.movies.injection.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sky.movies.features.list.viewmodel.MoviesViewModel
import sky.movies.utils.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun provideCategoriesViewModel(viewModel: MoviesViewModel): ViewModel
}