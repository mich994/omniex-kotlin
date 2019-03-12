package omniex.nl.omniex.data.model.products

import com.google.gson.annotations.SerializedName

data class Product(val id:String) {

    @SerializedName("product_id")
    var productId: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("manufacturer")
    var manufacturer: String? = null
    @SerializedName("model")
    var model: String? = null
    @SerializedName("image")
    var imageUrl: String? = null
    @SerializedName("images")
    var imageUrlsList: List<String>? = null
    @SerializedName("original_image")
    var originalImageUrl: String? = null
    @SerializedName("original_images")
    var originalImageUrlsList: List<String>? = null
    @SerializedName("price_excluding_tax")
    var priceExcTax: Double? = null
    @SerializedName("price_excluding_tax_formated")
    var priceExcTaxFormated: String? = null
    @SerializedName("rating")
    var rating: Double? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("minimum")
    var minimum: String? = null
    @SerializedName("meta_title")
    var metaTitle: String? = null
    @SerializedName("meta_description")
    var metaDescription: String? = null
    @SerializedName("tag")
    var tag: String? = null
    @SerializedName("stock_status")
    var stockStatus: String? = null
    @SerializedName("stock_status_id")
    var stockStatusId: Int? = null
    @SerializedName("manufacturer_id")
    var manufacturerId: Int? = null
    @SerializedName("tax_class_id")
    var taxClassId: Int? = null
    @SerializedName("date_available")
    var dateAvailable: String? = null
    @SerializedName("weight")
    var weight: String? = null
    @SerializedName("weight_class_id")
    var weightClassId: Int? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("date_modified")
    var dateModified: String? = null
    @SerializedName("viewed")
    var viewed: String? = null
    @SerializedName("weight_class")
    var weightClass: String? = null
    @SerializedName("length_class")
    var lenghtClass: String? = null
    @SerializedName("reward")
    var reward: String? = null
    @SerializedName("category")
    var categories: List<Category>? = null
    @SerializedName("quantity")
    var quantity: Int? = null
    @SerializedName("thumb")
    val imageThumb: String? = null
}
