package omniex.nl.omniex.ui.app.order.shipping

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseView

interface OrderShippingView : BaseView {

    fun onShippingAddressesFetched(addressList: List<Address>)
    fun onGetShippingAddressesFailed()
    fun onShippingAddressSet()
}
