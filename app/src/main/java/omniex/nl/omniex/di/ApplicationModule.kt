package omniex.nl.omniex.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [ActivitiesModule::class])
class ApplicationModule internal constructor(private val mContext: Context) {

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

}