package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.products.Product

class ProductsListResponse {

    @SerializedName("data")
    val productList: List<Product>? = null
}
