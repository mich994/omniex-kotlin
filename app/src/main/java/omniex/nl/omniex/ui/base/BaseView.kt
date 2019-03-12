package omniex.nl.omniex.ui.base

import com.hannesdorfmann.mosby3.mvp.MvpView
import omniex.nl.omniex.data.model.cart.Cart

interface BaseView : MvpView {
    fun onCartFetched(cart: Cart) {}
    fun startLoading() {}
    fun stopLoading() {}
}