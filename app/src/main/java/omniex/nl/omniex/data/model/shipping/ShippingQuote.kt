package omniex.nl.omniex.data.model.shipping

import com.google.gson.annotations.SerializedName

class ShippingQuote(@field:SerializedName("country_id")
                    val countryId: String, @field:SerializedName("zone_id")
                    val zoneId: String, @field:SerializedName("postcode")
                    val postcode: String)
