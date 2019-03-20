package omniex.nl.omniex.di

import android.content.Context
import android.support.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class Application : DaggerApplication() {

    val mInstance: Application
    get() = this


    override fun onCreate() {
        super.onCreate()

    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    val Context.application: Application
        get() = applicationContext as Application

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
                .appModule(ApplicationModule(this))
                .create(this)
    }
}
