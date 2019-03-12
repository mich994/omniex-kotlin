package omniex.nl.omniex.data.model.user

import com.google.gson.annotations.SerializedName

class CustomField {

    @SerializedName("custom_field_id")
    var customFieldId: String? = null
    @SerializedName("custom_field_value")
    var customFieldValue: List<String>? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("value")
    var value: String? = null
    @SerializedName("validation")
    var validation: String? = null
    @SerializedName("location")
    var location: String? = null
    @SerializedName("required")
    var required: Boolean? = null
    @SerializedName("sort_order")
    var sortOrder: String? = null
}
