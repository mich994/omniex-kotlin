package omniex.nl.omniex.data.model.shipping

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.address.Address

class ShippingAddressList {

    @SerializedName("address_id")
    val currentAddressId: String? = null
    @SerializedName("addresses")
    val addressList: ArrayList<Address>? = null
}
