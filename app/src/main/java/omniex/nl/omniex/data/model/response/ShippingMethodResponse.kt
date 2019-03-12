package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.shipping.ShippingMethodWrapper

class ShippingMethodResponse {

    @SerializedName("data")
    val shippingMethodWrapper: ShippingMethodWrapper? = null
}
