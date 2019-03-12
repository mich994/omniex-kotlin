package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("success")
    val success: Int? = null
    @SerializedName("error")
    val errors: List<String>? = null
}
