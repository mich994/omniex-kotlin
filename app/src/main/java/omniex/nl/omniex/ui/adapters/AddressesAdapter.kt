package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.AddressListItemView
import omniex.nl.omniex.ui.views.recycleritems.AddressListItemView_
import org.androidannotations.annotations.EBean

@EBean
open class AddressesAdapter : BaseRecyclerAdapter<Address, AddressListItemView>() {

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
