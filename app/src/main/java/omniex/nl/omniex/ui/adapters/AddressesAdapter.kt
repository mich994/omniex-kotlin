package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import nl.omniex.omniexshopping.data.model.address.Address
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter
import nl.omniex.omniexshopping.ui.views.recycleritems.AddressListItemView
import nl.omniex.omniexshopping.ui.views.recycleritems.AddressListItemView_
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.AddressListItemView

@EBean
class AddressesAdapter : BaseRecyclerAdapter<Address, AddressListItemView>() {

    private var mOnAddressListItemClickListener: OnAddressListItemClickListener? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): AddressListItemView {
        return AddressListItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<AddressListItemView>, position: Int) {
        holder.view.bind(getItem(position)).setOnAddressListItemClickListener(mOnAddressListItemClickListener)
    }

    fun setOnAddressListItemClickListener(onAddressListItemClickListener: OnAddressListItemClickListener) {
        mOnAddressListItemClickListener = onAddressListItemClickListener
    }

    interface OnAddressListItemClickListener {
        fun onEditClick(address: Address?)
        fun onRemoveClick(idToRemove: Int?)
    }
}
