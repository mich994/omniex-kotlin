package omniex.nl.omniex.data.model.order

import com.google.gson.annotations.SerializedName

data class OrderStatus(val id:String) {

    @SerializedName("name")
    val name: String? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("date_added")
    val dateAdded: String? = null
    @SerializedName("products")
    val products: String? = null
    @SerializedName("total")
    val totalPrice: String? = null
}
