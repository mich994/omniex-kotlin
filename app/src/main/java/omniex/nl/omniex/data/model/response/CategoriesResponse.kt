package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.products.Category

class CategoriesResponse {

    @SerializedName("data")
    var categoryList: List<Category>? = null
}
