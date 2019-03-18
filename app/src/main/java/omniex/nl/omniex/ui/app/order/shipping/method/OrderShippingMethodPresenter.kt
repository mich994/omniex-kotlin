package omniex.nl.omniex.ui.app.order.shipping.method

import java.util.ArrayList

import javax.inject.Inject

import nl.omniex.omniexshopping.data.model.response.ShippingMethodResponse
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethodSetter
import nl.omniex.omniexshopping.data.repository.ShippingRepository
import nl.omniex.omniexshopping.ui.base.BasePresenter

class OrderShippingMethodPresenter @Inject
internal constructor(private val mShippingRepository: ShippingRepository) : BasePresenter<OrderShippingMethodView>() {

    internal fun getShippingMethods() {
        addDisposable(mShippingRepository
                .getShippingMethods()
                .subscribe(
                        { shippingMethodResponse ->
                            if (shippingMethodResponse.code() === 200) {
                                ifViewAttached { view -> view.onShippingMethodsFetched(createList(shippingMethodResponse.body())) }
                            }
                        }, ???({ printStackTrace() })))
    }

    private fun createList(shippingMethodResponse: ShippingMethodResponse): List<ShippingMethod> {
        val shippingMethods = ArrayList<ShippingMethod>()
        shippingMethods.add(shippingMethodResponse.getShippingMethodWrapper().getShippingMethod())
        return shippingMethods
    }

    internal fun setShippingMethod(shippingMethodSetter: ShippingMethodSetter) {
        addDisposable(mShippingRepository
                .setShippingMethod(shippingMethodSetter)
                .subscribe(
                        { response ->
                            if (response.code() === 200)
                                ifViewAttached { view -> view.onShippingMethodSet() }
                        }, ???({ printStackTrace() })))
    }
}
