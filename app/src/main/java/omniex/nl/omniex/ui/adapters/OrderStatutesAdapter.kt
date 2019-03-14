package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import omniex.nl.omniex.data.model.order.OrderStatus
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.OrderStatusItemView

@EBean
class OrderStatutesAdapter : BaseRecyclerAdapter<OrderStatus, OrderStatusItemView>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): OrderStatusItemView {
        return OrderStatusItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<OrderStatusItemView>, position: Int) {
        holder.view.bind(getItem(position))
    }
}
