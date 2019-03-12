package omniex.nl.omniex.data.model.auth

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Login(@field:SerializedName("email")
            var email: String?, @field:SerializedName("password")
            var password: String?) : Serializable
