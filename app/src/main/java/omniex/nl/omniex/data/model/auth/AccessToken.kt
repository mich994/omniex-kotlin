package omniex.nl.omniex.data.model.auth

import com.google.gson.annotations.SerializedName

data class AccessToken(val id:String) {

    @SerializedName("access_token")
    var token: String? = null
    @SerializedName("expires_in")
    var expiresIn: Long? = null
    @SerializedName("token_type")
    var type: String? = null

    val accessToken: String
        get() = "$type $token"
}
