package omniex.nl.omniex.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributeMainMenuActivity(): MainMenuActivity_

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity_

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity_

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity(): RegisterActivity_

    @ContributesAndroidInjector
    abstract fun contributeEditAddressActivity(): EditAddressActivity_

    @ContributesAndroidInjector
    abstract fun contributeOrderActivity(): OrderActivity_

}
