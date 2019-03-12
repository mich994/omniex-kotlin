package omniex.nl.omniex.data.model.cart

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Cart(val id:String) : Serializable {

    @SerializedName("products")
    val cartProducts: ArrayList<CartProduct>? = null
    @SerializedName("total")
    val total: String? = null
}
