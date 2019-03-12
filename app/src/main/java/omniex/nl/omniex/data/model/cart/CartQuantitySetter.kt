package omniex.nl.omniex.data.model.cart

import com.google.gson.annotations.SerializedName

data class CartQuantitySetter(@field:SerializedName("key")
                         var productKey: String?, @field:SerializedName("quantity")
                         var quantitity: Int?)
