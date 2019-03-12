package omniex.nl.omniex.data.model.user

import com.google.gson.annotations.SerializedName

data class AccountCustomField(val id:String) {

    @SerializedName("2")
    var companyName: String? = null
    @SerializedName("1")
    var street: String? = null
    @SerializedName("4")
    var city: String? = null
    @SerializedName("5")
    var country: String? = null
    @SerializedName("3")
    var taxNumber: String? = null
}
