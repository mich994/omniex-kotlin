package omniex.nl.omniex.ui.app.order.shipping.method

import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.ui.base.BaseView

interface OrderShippingMethodView : BaseView {

    fun onShippingMethodsFetched(shippingMethods: List<ShippingMethod>)
    fun onShippingMethodSet()
}
