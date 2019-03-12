package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.address.AddressWrapper

data class AddressListResponse(@SerializedName("data") val addressWrapper: AddressWrapper? = null) {

}
