package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.shipping.ShippingAddressList

class OrderAddressesResponse {

    @SerializedName("data")
    val shippingAddressList: ShippingAddressList? = null
}
