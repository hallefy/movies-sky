package sky.movies.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import sky.movies.App
import sky.movies.injection.modules.ActivityModule
import sky.movies.injection.modules.ApplicationModule
import sky.movies.injection.modules.NetworkModule
import sky.movies.injection.modules.RequestModule
import sky.movies.injection.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = ([
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        RequestModule::class,
        ViewModelModule::class
    ])
)

interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}