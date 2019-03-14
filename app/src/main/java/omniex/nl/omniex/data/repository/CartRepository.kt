package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.cart.AddToCartModel
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartQuantitySetter
import omniex.nl.omniex.data.model.response.CartResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    val cart: Single<Response<CartResponse>>
        get() = mOmniexApi
                .getCart(mSharedPrefUtils.accessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun addToCart(addToCartModel: AddToCartModel): Single<Response<Void>> {
        return mOmniexApi
                .addToCart(mSharedPrefUtils.accessToken(), addToCartModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun emptyCart(): Single<Response<Void>> {
        return mOmniexApi
                .emptyCart(mSharedPrefUtils.accessToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateCartItemQuantity(cartQuantitySetter: CartQuantitySetter): Single<Response<Void>> {
        return mOmniexApi
                .updateCartItemQuantity(mSharedPrefUtils.accessToken(), cartQuantitySetter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    fun deleteCartItem(cartItemDelete: CartItemDelete): Single<Response<Void>> {
        return mOmniexApi
                .deleteCartItem(mSharedPrefUtils.accessToken(), cartItemDelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
