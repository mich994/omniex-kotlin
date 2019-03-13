package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.Constants.Companion.BASIC_TOKEN
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.auth.OldToken
import omniex.nl.omniex.data.model.response.AccessTokenResponse
import retrofit2.Response
import javax.inject.Inject

class TokenRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi) {

    fun accessToken(): Single<Response<AccessTokenResponse>> = mOmniexApi
            .getAccessToken(BASIC_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun refreshAccessToken(oldToken: OldToken): Single<Response<AccessTokenResponse>> {
        return mOmniexApi
                .refreshAccessToken(BASIC_TOKEN, oldToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
