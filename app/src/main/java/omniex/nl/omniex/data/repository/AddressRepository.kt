package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.address.AddAddress
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.model.order.OrderAddress
import omniex.nl.omniex.data.model.response.AddressListResponse
import omniex.nl.omniex.data.model.response.OrderAddressesResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class AddressRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    fun addressList(): Single<Response<AddressListResponse>> = mOmniexApi
                .getAddressList(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun shippingAddresses(): Single<Response<OrderAddressesResponse>> = mOmniexApi
                .getShippingAddresses(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun paymentAddresses(): Single<Response<OrderAddressesResponse>> = mOmniexApi
                .getPaymentAdresses(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun addNewAddress(addAddress: AddAddress): Single<Response<Void>> {
        return mOmniexApi
                .addNewAddress(mSharedPrefUtils.`accessToken()`, addAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun editAddress(addressId: Int?, addAddress: AddAddress): Single<Response<Void>> {
        return mOmniexApi
                .editAddress(mSharedPrefUtils.`accessToken()`, addressId, addAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun removeAddress(addressId: Int?): Single<Response<Void>> {
        return mOmniexApi
                .removeAddress(mSharedPrefUtils.`accessToken()`, addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAddressDetails(addressId: Int?): Single<Response<Void>> {
        return mOmniexApi
                .getAddressById(mSharedPrefUtils.`accessToken()`, addressId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addNewShippingAddress(orderAddress: OrderAddress): Single<Response<Void>> {
        return mOmniexApi
                .addNewShippingAddress(mSharedPrefUtils.`accessToken()`, orderAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun setExistingShippingAddress(existingAddress: Address): Single<Response<Void>> {
        return mOmniexApi
                .setExistingShippingAddress(mSharedPrefUtils.`accessToken()`, existingAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addNewPaymentAddress(orderAddress: OrderAddress): Single<Response<Void>> {
        return mOmniexApi
                .addNewPaymentAddress(mSharedPrefUtils.`accessToken()`, orderAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun setExistingPaymentAddress(existingAddress: Address): Single<Response<Void>> {
        return mOmniexApi
                .setExisitingPaymentAddress(mSharedPrefUtils.`accessToken()`, existingAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
