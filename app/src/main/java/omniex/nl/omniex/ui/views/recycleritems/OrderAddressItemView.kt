package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_order_address_item)
class OrderAddressItemView(context: Context) : CardView(context) {

    @ViewById(R.id.order_address_cd)
    lateinit var mOrderAddressCd: CardView

    @ViewById(R.id.order_address_name)
    lateinit var mName: TextView

    @ViewById(R.id.order_address_company)
    lateinit var mCompany: TextView

    @ViewById(R.id.order_address_street)
    lateinit var mStreet: TextView

    @ViewById(R.id.order_address_postcode_city)
    lateinit var mPostcodeCity: TextView

    private var mItemClickListener: BaseRecyclerAdapter.ItemClickListener<Address>? = null
    private var mAddress: Address? = null
    private val mPosition: Int = 0

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(address: Address, isSelected: Boolean): OrderAddressItemView {
        mOrderAddressCd!!.radius = 16f
        mAddress = address
        mName!!.setText(mAddress!!.firstName + " " + mAddress!!.lastName)
        mCompany!!.setText(mAddress!!.companyName)
        mStreet!!.setText(mAddress!!.addressOne + " " + mAddress!!.addressTwo)
        mPostcodeCity!!.setText(mAddress!!.postcode + ", " + mAddress!!.city)
        mOrderAddressCd!!.setBackgroundColor(if (isSelected) resources.getColor(R.color.colorPrimaryLight) else resources.getColor(R.color.icons))
        return this
    }

    fun setItemClickListener(itemClickListener: BaseRecyclerAdapter.ItemClickListener<Address>) {
        mItemClickListener = itemClickListener
    }

    @Click(R.id.order_address_cd)
    internal fun onClick() {
        mItemClickListener!!.onItemClick(mAddress)
    }
}
