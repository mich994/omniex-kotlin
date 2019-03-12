package omniex.nl.omniex.data.model.address

import com.google.gson.annotations.SerializedName

data class AddressWrapper(val id:String) {

    @SerializedName("addresses")
    val addressList: List<Address>? = null
}
