package omniex.nl.omniex.ui.app

import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject

class MainMenuPresenter @Inject
internal constructor(/*private val mProfileRepository: ProfileRepository, private val mCartRepository: CartRepository, private val mOrderRepository: OrderRepository*/) : BasePresenter<MainMenuView>() {

//    internal fun logout() {
//        addDisposable(mProfileRepository
//                .logout()
//                .subscribe(
//                        { voidResponse ->
//                            if (voidResponse.code() === 200) {
//                                SharedPrefUtils.setUserLogged(false)
//                                ifViewAttached(???({ it.onLogoutSuccess() }))
//                            }
//                        }, ???({ printStackTrace() })))
//    }
//
//    internal fun getCart() {
//        ifViewAttached(???({ BaseView.startLoading() }))
//        addDisposable(mCartRepository
//                .getCart()
//                .subscribe(
//                        { cartResponse ->
//                            ifViewAttached { view ->
//                                view.onCartFetched(cartResponse.body().getCart())
//                                view.stopLoading()
//                            }
//                        },
//                        { error ->
//                            error.printStackTrace()
//                            if (error is JsonSyntaxException)
//                                ifViewAttached { view -> view.onCartEmpty() }
//                            Timber.e(error)
//                            ifViewAttached(???({ BaseView.stopLoading() }))
//                        }))
//    }
//
//    internal fun updateCartQuantity(cartQuantitySetter: CartQuantitySetter) {
//        addDisposable(mCartRepository
//                .updateCartItemQuantity(cartQuantitySetter)
//                .subscribe({ voidResponse ->
//                    if (voidResponse.code() === 200)
//                        ifViewAttached(???({ it.onCartItemQuantityUpdated() }))
//                }, ???({ printStackTrace() })))
//    }
//
//    internal fun deleteCartItem(cartItemDelete: CartItemDelete) {
//        addDisposable(mCartRepository
//                .deleteCartItem(cartItemDelete)
//                .subscribe({ voidResponse ->
//                    if (voidResponse.code() === 200)
//                        ifViewAttached(???({ it.onCartItemQuantityUpdated() }))
//                }, ???({ printStackTrace() })))
//    }
}
