package omniex.nl.omniex.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import omniex.nl.omniex.ui.app.MainMenuActivity_

@Module
abstract class ActivitiesModule{
    @ContributesAndroidInjector
    abstract fun contributeMainMenuActivity(): MainMenuActivity_
}