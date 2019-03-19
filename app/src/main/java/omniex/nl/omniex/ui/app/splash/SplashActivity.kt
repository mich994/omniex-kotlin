package omniex.nl.omniex.ui.app.splash

import omniex.nl.omniex.R
import omniex.nl.omniex.ui.app.auth.StartActivity_
import omniex.nl.omniex.ui.app.main.MainMenuActivity_
import omniex.nl.omniex.ui.base.BaseActivity
import omniex.nl.omniex.utils.SharedPrefUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_splash)
open class SplashActivity : BaseActivity<SplashView, SplashPresenter>(), SplashView {

    @AfterViews
    internal fun getAccessToken() {
        getPresenter().resolveAccessToken()
    }

    override fun onTokenFetched() {
        finish()
        if (SharedPrefUtils.isUserLogged())
            MainMenuActivity_.intent(this).start()
        else
            StartActivity_.intent(this).start()
    }

    override fun onTokenError(error: String) {
        showToastMessage(error, 1)
    }
}
