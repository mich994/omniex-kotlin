package omniex.nl.omniex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import omniex.nl.omniex.di.modules.ActivitiesModule
import omniex.nl.omniex.di.modules.AdaptersModule
import omniex.nl.omniex.di.modules.ApiModule
import omniex.nl.omniex.di.modules.FragmentsModule

@Module(includes = [ActivitiesModule::class, FragmentsModule::class, AdaptersModule::class, ApiModule::class])
class ApplicationModule internal constructor(private val mContext: Context) {

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

}