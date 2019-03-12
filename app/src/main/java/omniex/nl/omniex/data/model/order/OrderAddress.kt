package omniex.nl.omniex.data.model.order

import com.google.gson.annotations.SerializedName

data class OrderAddress(@field:SerializedName("firstname")
                   var firstName: String?, @field:SerializedName("lastname")
                   var lastName: String?, @field:SerializedName("city")
                   var city: String?, @field:SerializedName("address_1")
                   var addressOne: String?, @field:SerializedName("address_2")
                   var addressTwo: String?, @field:SerializedName("country_id")
                   var countryId: String?, @field:SerializedName("postcode")
                   var postcode: String?, @field:SerializedName("zone_id")
                   var zoneId: String?)
