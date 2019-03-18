package omniex.nl.omniex.ui.app.profile.order

import omniex.nl.omniex.data.model.order.OrderStatus
import omniex.nl.omniex.ui.base.BaseView


interface ProfileOrderView : BaseView {

    fun onOrderStatusesFetched(orderStatusList: List<OrderStatus>)
}
