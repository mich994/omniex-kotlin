package omniex.nl.omniex.ui.app.order.shipping

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.adapters.OrderAddressesAdapter
import omniex.nl.omniex.ui.app.order.OrderActivity
import omniex.nl.omniex.ui.app.order.payment.OrderPaymentFragment_
import omniex.nl.omniex.ui.app.profile.address.edit.EditAddressActivity_
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_order_shipping)
open class OrderShippingFragment : BaseFragment<OrderShippingView, OrderShippingPresenter>(), OrderShippingView, BaseRecyclerAdapter.ItemClickListener<Address> {

    @ViewById(R.id.order_shipping_rv)
    lateinit var mShippingAddressRv: RecyclerView

    @ViewById(R.id.order_shipping_empty_tv)
    lateinit var mEmptyListTv: TextView

    @ViewById(R.id.order_shipping_next_btn)
    lateinit var mNextBtn: Button

    @Inject
    lateinit var mOrderAddressesAdapter: OrderAddressesAdapter

    private val mSelectedAddressId: String? = null
    private var mSelectedAddress: Address? = null
    private var mOrderActivity: OrderActivity? = null

   override fun onResume() {
        super.onResume()
        if (mOrderAddressesAdapter != null)
            getPresenter().getShippingAddresses()
        mOrderActivity = getActivity() as OrderActivity
    }

    @AfterViews
    fun initAddressList() {
        mOrderAddressesAdapter!!.resetSelection()
        mOrderAddressesAdapter!!.setClickListener(this)
        mShippingAddressRv!!.adapter = mOrderAddressesAdapter
    }

    @Click(R.id.order_shipping_empty_tv, R.id.order_shipping_next_btn)
    fun onClick(v: View) {
        if (v.id == R.id.order_shipping_empty_tv)
            EditAddressActivity_.intent(getActivity()).mIsNewAddress(true).start()
        else if (v.id == R.id.order_shipping_next_btn) {
            if (mSelectedAddress != null)
                getPresenter().setShippingAddress(mSelectedAddress!!)
        }
    }

    override fun onShippingAddressesFetched(addressList: List<Address>) {
        mOrderAddressesAdapter!!.setItems(addressList)
        mEmptyListTv!!.visibility = View.GONE
    }

    override fun onGetShippingAddressesFailed() {
        mEmptyListTv!!.visibility = View.VISIBLE
    }

    override fun onShippingAddressSet() {
        mOrderActivity!!.setShippingAddress(mSelectedAddress!!)
        goToFragment(OrderPaymentFragment_.builder().build(), true)
    }

    override fun onItemClick(address: Address?) {
        mOrderAddressesAdapter!!.setSelected(mOrderAddressesAdapter!!.items.indexOf(address))
        mSelectedAddress = address
        mNextBtn!!.alpha = 1f
    }
}
