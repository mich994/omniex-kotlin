package omniex.nl.omniex.data.model.auth.register

import com.google.gson.annotations.SerializedName

data class CustomFieldSetter(//street
        @field:SerializedName("1")
        var street: String?, //company name
        @field:SerializedName("2")
        var companyName: String?, //tax
        @field:SerializedName("3")
        var tax: String?, //city
        @field:SerializedName("4")
        var city: String?, //country
        @field:SerializedName("5")
        var country: String?)
