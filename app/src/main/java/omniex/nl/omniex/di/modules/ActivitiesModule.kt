package omniex.nl.omniex.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import omniex.nl.omniex.ui.app.auth.login.LoginActivity_
import omniex.nl.omniex.ui.app.auth.register.RegisterActivity_
import omniex.nl.omniex.ui.app.main.MainMenuActivity_
import omniex.nl.omniex.ui.app.order.OrderActivity_
import omniex.nl.omniex.ui.app.profile.address.edit.EditAddressActivity_
import omniex.nl.omniex.ui.app.splash.SplashActivity_


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
