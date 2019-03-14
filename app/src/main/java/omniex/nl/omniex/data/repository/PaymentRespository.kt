package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.payment.PaymentMethodSetter
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class PaymentRespository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    fun paymentMethods(): Single<Response<Void>> = mOmniexApi
                .getPaymentMethods(mSharedPrefUtils.accessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun setPaymentMethod(paymentMethodSetter: PaymentMethodSetter): Single<Response<Void>> {
        return mOmniexApi
                .setPaymentMethod(mSharedPrefUtils.accessToken(), paymentMethodSetter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
