package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName
import java.util.*

data class Zone(val id:String) {

    @SerializedName("country_id")
    val countryId: Int? = null
    @SerializedName("country_name")
    val countryName: String? = null
    @SerializedName("zone")
    private val mProvinceList: ArrayList<Province>? = null

    val provinceList: List<Province>?
        get() = mProvinceList
}
