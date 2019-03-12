package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.products.FeaturedProductWrapper

class FeaturedProductsResponse {

    @SerializedName("success")
    var success: Int? = null
    @SerializedName("error")
    var errors: List<String>? = null
    @SerializedName("data")
    var productWrappers: List<FeaturedProductWrapper>? = null
}
