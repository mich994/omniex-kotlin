package omniex.nl.omniex.data.model.shipping

import com.google.gson.annotations.SerializedName

class ShippingMethodSetter(@field:SerializedName("shipping_method")
                           var shippingMethod: String?, @field:SerializedName("comment")
                           var comment: String?)
