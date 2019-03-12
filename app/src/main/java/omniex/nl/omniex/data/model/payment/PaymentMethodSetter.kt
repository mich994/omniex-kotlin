package omniex.nl.omniex.data.model.payment

import com.google.gson.annotations.SerializedName

data class PaymentMethodSetter(@field:SerializedName("payment_method")
                          var code: String?, @field:SerializedName("agree")
                          var agree: String?, @field:SerializedName("comment")
                          var comment: String?)
