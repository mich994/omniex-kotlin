package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Country(@field:SerializedName("country_id")
              val countryId: Int?, @field:SerializedName("name")
              val name: String, @field:SerializedName("iso_code_2")
              val isoCode: String) : Serializable {

    val fullName: String
        get() = "$name, $isoCode"
}
