package omniex.nl.omniex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import omniex.nl.omniex.di.modules.ActivitiesModule
import omniex.nl.omniex.di.modules.ApiModule

@Module(includes = [ActivitiesModule::class, ApiModule::class])
class ApplicationModule internal constructor(private val mContext: Context) {

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

}