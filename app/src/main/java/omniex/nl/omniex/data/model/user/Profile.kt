package omniex.nl.omniex.data.model.user

import com.google.gson.annotations.SerializedName

class Profile {

    @SerializedName("customer_id")
    var customerId: String? = null
    @SerializedName("customer_group_id")
    var customerGroupId: String? = null
    @SerializedName("store_id")
    var storeId: String? = null
    @SerializedName("language_id")
    var languageId: String? = null
    @SerializedName("firstname")
    var firstName: String? = null
    @SerializedName("lastname")
    var lastName: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("telephone")
    var telephone: String? = null
    @SerializedName("fax")
    var fax: String? = null
    @SerializedName("newsletter")
    var newsletter: String? = null
    @SerializedName("address_id")
    var addressId: String? = null
    @SerializedName("ip")
    var ip: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("safe")
    var safe: String? = null
    @SerializedName("code")
    var code: String? = null
    @SerializedName("date_added")
    var dateAdded: String? = null
    @SerializedName("custom_fields")
    var customFields: List<CustomField>? = null
    @SerializedName("account_custom_field")
    var accountCustomField: AccountCustomField? = null
    @SerializedName("wishlist_total")
    var wishlistTotal: String? = null
    @SerializedName("cart_count_products")
    var cartCount: Int? = null
}
