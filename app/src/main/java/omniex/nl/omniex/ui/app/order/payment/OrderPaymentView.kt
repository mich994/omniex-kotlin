package omniex.nl.omniex.ui.app.order.payment

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseView


interface OrderPaymentView : BaseView {

    fun onPaymentAddressesFetched(addressList: List<Address>)

    fun onPaymentMethodSet()
}
