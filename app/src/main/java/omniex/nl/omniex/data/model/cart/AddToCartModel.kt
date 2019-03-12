package omniex.nl.omniex.data.model.cart

import com.google.gson.annotations.SerializedName

data class AddToCartModel(@field:SerializedName("product_id")
                     private val mProductId: String, @field:SerializedName("quantity")
                     private val mQuantity: String)
