package omniex.nl.omniex.data.model.auth

import com.google.gson.annotations.SerializedName

data class OldToken(@field:SerializedName("old_token")
               var oldToken: String?)
