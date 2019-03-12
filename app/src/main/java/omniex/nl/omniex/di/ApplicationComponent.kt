package omniex.nl.omniex.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class, ApplicationModule::class])
 interface ApplicationComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>() {
        internal abstract fun appModule(applicationModule: ApplicationModule): ApplicationComponent.Builder
    }
}
