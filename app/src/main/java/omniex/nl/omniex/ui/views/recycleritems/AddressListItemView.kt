package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.adapters.AddressesAdapter
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_address_list_item)
open class AddressListItemView(context: Context) : FrameLayout(context) {

    @ViewById(R.id.address_item_name)
    lateinit var mAddressName: TextView

    private var mOnAddressListItemClickListener: AddressesAdapter.OnAddressListItemClickListener? = null
    private var mAddress: Address? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(address: Address?): AddressListItemView {
        mAddress = address
        mAddressName!!.setText(mAddress!!.addressThumb)
        return this
    }

    fun setOnAddressListItemClickListener(onAddressListItemClickListener: AddressesAdapter.OnAddressListItemClickListener?) {
        mOnAddressListItemClickListener = onAddressListItemClickListener
    }

    @Click(R.id.address_item_edit, R.id.address_item_remove)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.address_item_edit -> mOnAddressListItemClickListener!!.onEditClick(mAddress)
            R.id.address_item_remove -> mOnAddressListItemClickListener!!.onRemoveClick(Integer.valueOf(mAddress!!.addressId))
        }
    }
}
