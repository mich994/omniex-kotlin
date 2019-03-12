package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.cart.Cart

class CartResponse {

    @SerializedName("data")
    val cart: Cart? = null
}
