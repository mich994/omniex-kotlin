package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.products.Product

class ProductResponse {

    @SerializedName("data")
    var product: Product? = null
}
