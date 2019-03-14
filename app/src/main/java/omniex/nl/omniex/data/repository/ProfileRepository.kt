package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.auth.Login
import omniex.nl.omniex.data.model.auth.register.RegisterSetter
import omniex.nl.omniex.data.model.response.LoginResponse
import omniex.nl.omniex.data.model.response.RegisterResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    fun login(login: Login): Single<Response<LoginResponse>> {
        return mOmniexApi
                .login(mSharedPrefUtils.accessToken(), login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout(): Single<Response<Void>> {
        return mOmniexApi
                .logout(mSharedPrefUtils.accessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(registerSetter: RegisterSetter): Single<Response<RegisterResponse>> {
        return mOmniexApi
                .register(mSharedPrefUtils.accessToken(), registerSetter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
