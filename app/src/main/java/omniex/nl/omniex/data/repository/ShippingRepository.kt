package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.response.ShippingMethodResponse
import omniex.nl.omniex.data.model.shipping.ShippingMethodSetter
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class ShippingRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    val shippingMethods: Single<Response<ShippingMethodResponse>>
        get() = mOmniexApi
                .getShippingMethods(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun setShippingMethod(shippingMethodSetter: ShippingMethodSetter): Single<Response<Void>> {
        return mOmniexApi
                .setShippingMethod(mSharedPrefUtils.`accessToken()`, shippingMethodSetter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
