package omniex.nl.omniex.ui.app.main

import com.google.gson.JsonSyntaxException
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartQuantitySetter
import omniex.nl.omniex.data.repository.CartRepository
import omniex.nl.omniex.data.repository.OrderRepository
import omniex.nl.omniex.data.repository.ProfileRepository
import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.utils.SharedPrefUtils
import timber.log.Timber
import javax.inject.Inject

class MainMenuPresenter @Inject
internal constructor(private val mProfileRepository: ProfileRepository, private val mCartRepository: CartRepository, private val mOrderRepository: OrderRepository) : BasePresenter<MainMenuView>() {

    fun logout() {
        addDisposable(mProfileRepository
                .logout()
                .subscribe(
                        { voidResponse ->
                            if (voidResponse.code() === 200) {
                                SharedPrefUtils.setUserLogged(false)
                                ifViewAttached { it.onLogoutSuccess() }
                            }
                        }, {
                    it.printStackTrace()
                }))
    }

    fun getCart() {
        ifViewAttached { it.startLoading() }
        addDisposable(mCartRepository
                .cart
                .subscribe(
                        { cartResponse ->
                            ifViewAttached { view ->
                                view.onCartFetched(cartResponse.body()!!.cart!!)
                                view.stopLoading()
                            }
                        },
                        { error ->
                            error.printStackTrace()
                            if (error is JsonSyntaxException)
                                ifViewAttached { view -> view.onCartEmpty() }
                            Timber.e(error)
                            ifViewAttached { it.stopLoading() }
                        }))
    }

    fun updateCartQuantity(cartQuantitySetter: CartQuantitySetter) {
        addDisposable(mCartRepository
                .updateCartItemQuantity(cartQuantitySetter)
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { it.onCartItemQuantityUpdated() }
                }, { it.printStackTrace() }))
    }

    fun deleteCartItem(cartItemDelete: CartItemDelete) {
        addDisposable(mCartRepository
                .deleteCartItem(cartItemDelete)
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { it.onCartItemQuantityUpdated() }
                }, { it.printStackTrace() }))
    }
}
