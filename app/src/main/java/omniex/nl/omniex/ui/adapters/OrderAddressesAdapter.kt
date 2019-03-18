package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.OrderAddressItemView

@EBean
class OrderAddressesAdapter : BaseRecyclerAdapter<Address, OrderAddressItemView>() {

    private var mSelected = -1

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): OrderAddressItemView {
        return OrderAddressItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<OrderAddressItemView>, position: Int) {
        holder.view.bind(getItem(position), position == mSelected).setItemClickListener(itemClickListener)
    }

    fun setSelected(selected: Int) {
        mSelected = selected
        notifyDataSetChanged()
    }

    fun resetSelection() {
        mSelected = -1
    }
}
