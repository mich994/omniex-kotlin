package omniex.nl.omniex.data.model.cart

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class CartProduct(val id:String) : Serializable {

    @SerializedName("key")
    var key: String? = null
    @SerializedName("thumb")
    var thumb: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("product_id")
    var productId: String? = null
    @SerializedName("quantity")
    var quantity: String? = null
    @SerializedName("stock")
    var inStock: Boolean? = null
    @SerializedName("price")
    var price: String? = null
    @SerializedName("total")
    var totalPrice: String? = null
}
