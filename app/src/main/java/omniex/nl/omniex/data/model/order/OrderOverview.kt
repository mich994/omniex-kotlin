package omniex.nl.omniex.data.model.order

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.model.cart.Cart
import omniex.nl.omniex.data.model.shipping.ShippingMethod


data class OrderOverview(val id:String) {

    var cart: Cart? = null
    var shippingAddress: Address? = null
    var paymentAddress: Address? = null
    var shippingMethod: ShippingMethod? = null
    var paymentMethod: String? = null
}
