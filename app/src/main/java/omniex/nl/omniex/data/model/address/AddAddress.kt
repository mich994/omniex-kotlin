package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName

data class AddAddress(@field:SerializedName("firstname")
                 val firstName: String, @field:SerializedName("lastname")
                 val lastName: String, @field:SerializedName("city")
                 val city: String, @field:SerializedName("address_1")
                 val addressOne: String, @field:SerializedName("address_2")
                 val addressTwo: String, @field:SerializedName("country_id")
                 val countryId: String, @field:SerializedName("postcode")
                 val postcode: String, @field:SerializedName("zone_id")
                 val zoneId: String, @field:SerializedName("company")
                 val company: String) {
    @SerializedName("default")
    val default: Int?

    init {
        default = 0
    }
}
