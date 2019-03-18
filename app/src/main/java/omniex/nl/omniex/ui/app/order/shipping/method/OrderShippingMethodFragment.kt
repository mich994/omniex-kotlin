package omniex.nl.omniex.ui.app.order.shipping.method

import android.icu.lang.UScript.getCode
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import com.hannesdorfmann.mosby3.PresenterManager.getPresenter
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.data.model.shipping.ShippingMethodSetter
import omniex.nl.omniex.ui.adapters.ShippingMethodsAdapter
import omniex.nl.omniex.ui.app.order.OrderActivity
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import javax.inject.Inject

@EFragment(R.layout.fragment_order_shipping)
class OrderShippingMethodFragment : BaseFragment<OrderShippingMethodView, OrderShippingMethodPresenter>(), OrderShippingMethodView, BaseRecyclerAdapter.ItemClickListener<ShippingMethod> {

    @ViewById(R.id.order_shipping_rv)
    lateinit var mShippingAddressRv: RecyclerView

    @ViewById(R.id.order_shipping_empty_tv)
    lateinit var mEmptyListTv: TextView

    @ViewById(R.id.order_shipping_next_btn)
    lateinit var mNextBtn: Button

    @Inject
    lateinit var mShippingMethodsAdapter: ShippingMethodsAdapter

    private var mSelectedMethod: ShippingMethod? = null
    private var mOrderActivity: OrderActivity? = null

   override fun onResume() {
        super.onResume()
        mOrderActivity = getActivity() as OrderActivity
    }

    @AfterViews
    internal fun initShippingMethodsList() {
        mShippingMethodsAdapter!!.setClickListener(this)
        mShippingAddressRv!!.adapter = mShippingMethodsAdapter
        getPresenter().getShippingMethods()
    }

    @Click(R.id.order_shipping_next_btn)
    internal fun onClick() {
        if (mSelectedMethod != null)
            getPresenter().setShippingMethod(ShippingMethodSetter(mSelectedMethod!!.weight!!.quote!!.weightCode!!.code, ""))
    }

    override fun onShippingMethodsFetched(shippingMethods: List<ShippingMethod>) {
        mShippingMethodsAdapter!!.setItems(shippingMethods)
    }

    override fun onShippingMethodSet() {
        mOrderActivity!!.setShippingMethod(mSelectedMethod!!)
        goToFragment(OrderPaymentMethodsFragment_.builder().build(), true)
    }

    fun onItemClick(shippingMethod: ShippingMethod) {
        mSelectedMethod = shippingMethod
        mShippingMethodsAdapter!!.setSelection(mShippingMethodsAdapter!!.items.indexOf(shippingMethod))
        mNextBtn!!.alpha = 1f
    }
}
