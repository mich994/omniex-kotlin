package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.ShippingMethodItemView

@EBean
class ShippingMethodsAdapter : BaseRecyclerAdapter<ShippingMethod, ShippingMethodItemView>() {

    private var mSeleceted = -1

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ShippingMethodItemView {
        return ShippingMethodItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<ShippingMethodItemView>, position: Int) {
        holder.view.bind(getItem(position)).setItemClickListener(getItemClickListener()).setSelected(mSeleceted == position)
    }

    fun setSelection(position: Int) {
        mSeleceted = position
        notifyDataSetChanged()
    }

    fun resetSelection() {
        mSeleceted = -1
    }
}
