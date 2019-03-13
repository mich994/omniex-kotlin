package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.response.OrderStatusesResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class OrderRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    val orderOverview: Single<Response<Void>>
        get() = mOmniexApi
                .getOrderOverview(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    val orderStatuses: Single<Response<OrderStatusesResponse>>
        get() = mOmniexApi
                .getOrderStatuses(mSharedPrefUtils.`accessToken()`, 20, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun simpleConfirm(): Single<Response<Void>> {
        return mOmniexApi
                .simpleConfirmOverview(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun confirmOrder(): Single<Response<Void>> {
        return mOmniexApi
                .confirmOrder(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getOrderDetails(id: Int?): Single<Response<Void>> {
        return mOmniexApi
                .getOrderDetails(mSharedPrefUtils.`accessToken()`, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
