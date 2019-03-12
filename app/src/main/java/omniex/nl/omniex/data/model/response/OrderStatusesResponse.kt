package omniex.nl.omniex.data.model.response

import com.google.gson.annotations.SerializedName
import omniex.nl.omniex.data.model.order.OrderStatus

class OrderStatusesResponse {

    @SerializedName("data")
    val orderStatusList: List<OrderStatus>? = null
}
