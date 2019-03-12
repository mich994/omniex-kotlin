package omniex.nl.omniex.data.model.products

import com.google.gson.annotations.SerializedName

data class Category(val objId:String) {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("category_id")
    var categoryId: Int? = null
    @SerializedName("image")
    var imageUrl: String? = null
}
