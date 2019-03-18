package omniex.nl.omniex.ui.app.order.shipping.method

import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod
import nl.omniex.omniexshopping.ui.base.BaseView

interface OrderShippingMethodView : BaseView {

    fun onShippingMethodsFetched(shippingMethods: List<ShippingMethod>)
    fun onShippingMethodSet()
}
