package omniex.nl.omniex.data.model.shipping

import com.google.gson.annotations.SerializedName

data class Weight(@SerializedName("title") val title: String? = null, @SerializedName("quote") val quote: Quote? = null)
