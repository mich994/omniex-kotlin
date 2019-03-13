package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.response.CountryResponse
import omniex.nl.omniex.data.model.response.ZoneResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class CountriesRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    val countries: Single<Response<CountryResponse>>
        get() = mOmniexApi
                .getCountries(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getZones(countryId: Int?): Single<Response<ZoneResponse>> {
        return mOmniexApi
                .getZones(mSharedPrefUtils.`accessToken()`, countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
