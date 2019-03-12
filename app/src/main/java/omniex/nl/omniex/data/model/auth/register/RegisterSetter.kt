package omniex.nl.omniex.data.model.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterSetter(@field:SerializedName("firstname")
                     var firstName: String?, @field:SerializedName("lastname")
                     var lastName: String?, @field:SerializedName("email")
                     var email: String?, @field:SerializedName("password")
                     var password: String?, @field:SerializedName("confirm")
                     var confirm: String?, @field:SerializedName("telephone")
                     var telephone: String?, @field:SerializedName("agree")
                     var agree: Int?, @field:SerializedName("custom_field")
                     var customField: CustomField?)
