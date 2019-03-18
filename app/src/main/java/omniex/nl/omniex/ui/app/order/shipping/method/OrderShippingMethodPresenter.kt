package omniex.nl.omniex.ui.app.order.shipping.method

import omniex.nl.omniex.data.model.response.ShippingMethodResponse
import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.data.model.shipping.ShippingMethodSetter
import omniex.nl.omniex.data.repository.ShippingRepository
import omniex.nl.omniex.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class OrderShippingMethodPresenter @Inject
internal constructor(private val mShippingRepository: ShippingRepository) : BasePresenter<OrderShippingMethodView>() {

    internal fun getShippingMethods() {
        addDisposable(mShippingRepository
                .shippingMethods
                .subscribe(
                        { shippingMethodResponse ->
                            if (shippingMethodResponse.code() === 200) {
                                ifViewAttached { view -> view.onShippingMethodsFetched(createList(shippingMethodResponse.body()!!)) }
                            }
                        }, { it.printStackTrace() }))
    }

    private fun createList(shippingMethodResponse: ShippingMethodResponse): List<ShippingMethod> {
        val shippingMethods = ArrayList<ShippingMethod>()
        shippingMethods.add(shippingMethodResponse.shippingMethodWrapper!!.shippingMethod!!)
        return shippingMethods
    }

    internal fun setShippingMethod(shippingMethodSetter: ShippingMethodSetter) {
        addDisposable(mShippingRepository
                .setShippingMethod(shippingMethodSetter)
                .subscribe(
                        { response ->
                            if (response.code() === 200)
                                ifViewAttached { view -> view.onShippingMethodSet() }
                        }, { it.printStackTrace() }))
    }
}
