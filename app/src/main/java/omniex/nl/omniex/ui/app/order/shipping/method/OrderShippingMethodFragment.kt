package omniex.nl.omniex.ui.app.order.shipping.method

import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import javax.inject.Inject

import nl.omniex.omniexshopping.R
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethodSetter
import nl.omniex.omniexshopping.ui.adapters.ShippingMethodsAdapter
import nl.omniex.omniexshopping.ui.app.order.OrderActivity
import nl.omniex.omniexshopping.ui.app.order.payment.method.OrderPaymentMethodsFragment_
import nl.omniex.omniexshopping.ui.base.BaseFragment
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter

@EFragment(R.layout.fragment_order_shipping)
class OrderShippingMethodFragment : BaseFragment<OrderShippingMethodView, OrderShippingMethodPresenter>(), OrderShippingMethodView, BaseRecyclerAdapter.ItemClickListener<ShippingMethod> {

    @ViewById(R.id.order_shipping_rv)
    internal var mShippingAddressRv: RecyclerView? = null

    @ViewById(R.id.order_shipping_empty_tv)
    internal var mEmptyListTv: TextView? = null

    @ViewById(R.id.order_shipping_next_btn)
    internal var mNextBtn: Button? = null

    @Inject
    internal var mShippingMethodsAdapter: ShippingMethodsAdapter? = null

    private var mSelectedMethod: ShippingMethod? = null
    private var mOrderActivity: OrderActivity? = null

    fun onResume() {
        super.onResume()
        mOrderActivity = getActivity() as OrderActivity
    }

    @AfterViews
    internal fun initShippingMethodsList() {
        mShippingMethodsAdapter!!.setItemClickListener(this)
        mShippingAddressRv!!.adapter = mShippingMethodsAdapter
        getPresenter().getShippingMethods()
    }

    @Click(R.id.order_shipping_next_btn)
    internal fun onClick() {
        if (mSelectedMethod != null)
            getPresenter().setShippingMethod(ShippingMethodSetter(mSelectedMethod!!.getWeight().getQuote().getWeightCode().getCode(), ""))
    }

    fun onShippingMethodsFetched(shippingMethods: List<ShippingMethod>) {
        mShippingMethodsAdapter!!.setItems(shippingMethods)
    }

    override fun onShippingMethodSet() {
        mOrderActivity!!.setShippingMethod(mSelectedMethod)
        goToFragment(OrderPaymentMethodsFragment_.builder().build(), true)
    }

    fun onItemClick(shippingMethod: ShippingMethod) {
        mSelectedMethod = shippingMethod
        mShippingMethodsAdapter!!.setSelection(mShippingMethodsAdapter!!.getItems().indexOf(shippingMethod))
        mNextBtn!!.alpha = 1f
    }
}
