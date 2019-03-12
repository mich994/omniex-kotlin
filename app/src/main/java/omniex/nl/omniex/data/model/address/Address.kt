package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Address(val id:String) : Serializable {

    @SerializedName("address_id")
    val addressId: String? = null
    @SerializedName("firstname")
    val firstName: String? = null
    @SerializedName("lastname")
    val lastName: String? = null
    @SerializedName("company")
    val companyName: String? = null
    @SerializedName("address_1")
    val addressOne: String? = null
    @SerializedName("address_2")
    val addressTwo: String? = null
    @SerializedName("postcode")
    val postcode: String? = null
    @SerializedName("city")
    val city: String? = null
    @SerializedName("zone_id")
    val zoneId: String? = null
    @SerializedName("zone")
    val zoneName: String? = null
    @SerializedName("zone_code")
    val zoneCode: String? = null
    @SerializedName("country_id")
    val countryId: String? = null
    @SerializedName("country")
    val country: String? = null
    @SerializedName("iso_code_2")
    val isoCodeTwo: String? = null

    val addressThumb: String
        get() = "$addressOne $addressTwo, $city, $zoneName, $country"

    val countryNameFormated: String
        get() = "$country, $isoCodeTwo"

    val zoneNameFormated: String
        get() = "$zoneName, $zoneCode"

    override fun hashCode(): Int {
        var code = 0
        code = city!!.hashCode()
        code += addressOne!!.hashCode()
        code += postcode!!.hashCode()
        return code
    }

    override fun equals(obj: Any?): Boolean {
        val address = obj as Address?
        return address!!.city == city && address.addressOne == addressOne && address.postcode == postcode
    }
}
