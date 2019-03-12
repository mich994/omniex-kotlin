package omniex.nl.omniex.data.model.products

import com.google.gson.annotations.SerializedName

data class FeaturedProductWrapper(val id:String) {

    @SerializedName("module_id")
    var moduleId: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("code")
    var code: String? = null
    @SerializedName("products")
    var featuredProducts: List<FeaturedProduct>? = null
}
