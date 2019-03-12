package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.address.Country
import java.util.*

class CountryResponse {

    @SerializedName("data")
    private val mCountryList: ArrayList<Country>? = null

    val countryList: List<Country>?
        get() = mCountryList
}
