package omniex.nl.omniex.data.model.products

import com.google.gson.annotations.SerializedName

data class FeaturedProduct(val id: String, @SerializedName("name") var name: String? = null, @SerializedName("thumb") var thumb: String? = null, @SerializedName("product_id") var productId: Int? = null, @SerializedName("price_excluding_tax_formated") var priceExcludingTaxFormatted: String? = null, @SerializedName("description") var description: String? = null) {

}
