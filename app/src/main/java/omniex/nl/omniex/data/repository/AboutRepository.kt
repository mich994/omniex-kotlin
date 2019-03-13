package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class AboutRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    fun information(): Single<Response<Void>>
            = mOmniexApi
            .getInformation(mSharedPrefUtils.`accessToken()`)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getInformationById(id: Int?): Single<Response<Void>> {
        return mOmniexApi
                .getInformationById(mSharedPrefUtils.`accessToken()`, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}
