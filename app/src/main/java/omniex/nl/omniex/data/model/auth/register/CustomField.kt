package omniex.nl.omniex.data.model.auth.register

import com.google.gson.annotations.SerializedName

data class CustomField(@field:SerializedName("account")
                  private val mCustomFieldSetter: CustomFieldSetter)
