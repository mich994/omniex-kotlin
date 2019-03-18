package omniex.nl.omniex.ui.app.profile.address

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseView

interface ProfileAddressView : BaseView {
    fun onAddressListFetched(addressList: List<Address>)
    fun onAddressRemoved()
}
