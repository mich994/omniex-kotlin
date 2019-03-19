package omniex.nl.omniex.ui.app.splash

import omniex.nl.omniex.ui.base.BaseView

interface SplashView : BaseView {

    fun onTokenFetched()

    fun onTokenError(error: String)
}
