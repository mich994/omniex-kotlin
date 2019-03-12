package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.user.Profile

class LoginResponse {

    @SerializedName("success")
    var success: Int? = null
    @SerializedName("error")
    var error: List<String>? = null
    @SerializedName("data")
    var profile: Profile? = null
}
