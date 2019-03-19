package omniex.nl.omniex.ui.app.splash

import omniex.nl.omniex.data.model.auth.OldToken
import omniex.nl.omniex.data.model.response.AccessTokenResponse
import omniex.nl.omniex.data.repository.TokenRepository
import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SplashPresenter @Inject
internal constructor(private val mTokenRepository: TokenRepository, private val mSharedPrefUtils: SharedPrefUtils) : BasePresenter<SplashView>() {

    internal fun resolveAccessToken() {
        if (mSharedPrefUtils.accessToken().isEmpty())
            getAccessToken()
        else
            refreshAccessToken()
    }

    private fun getAccessToken() {
        addDisposable(mTokenRepository
                .accessToken()
                .subscribe(
                        { this.handleTokenResponse(it) },
        { error ->
            ifViewAttached { view -> view.onTokenError(error.message!!) }
            Timber.e(error)
        })
        )
    }

    private fun refreshAccessToken() {
        addDisposable(mTokenRepository
                .refreshAccessToken(OldToken(mSharedPrefUtils.oldToken()))
                .subscribe(
                        { this.handleTokenResponse(it) },
        { error ->
            ifViewAttached { view -> view.onTokenError(error.message!!) }
            Timber.e(error)
        })
        )
    }

    private fun handleTokenResponse(tokenResponse: Response<AccessTokenResponse>) {
        if (tokenResponse.isSuccessful() && tokenResponse.code() == 200) {
            mSharedPrefUtils.saveAccessToken(tokenResponse.body()!!.accessToken!!.accessToken)
            mSharedPrefUtils.saveOldToken(tokenResponse.body()!!.accessToken!!.token!!)
            ifViewAttached({ it.onTokenFetched() })
        } else
            ifViewAttached { view -> view.onTokenError(tokenResponse.message()) }
    }
}
