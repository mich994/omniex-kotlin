package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.auth.AccessToken

data class AccessTokenResponse(val id:String, @SerializedName("data") val accessToken: AccessToken? = null) {

}
