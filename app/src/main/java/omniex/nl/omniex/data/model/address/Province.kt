package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Province(@field:SerializedName("zone_id")
               val zoneId: String, @field:SerializedName("country_id")
               val countryId: String, @field:SerializedName("name")
               val name: String, @field:SerializedName("code")
               val code: String) : Serializable {

    val fullName: String
        get() = "$name, $code"
}
