package omniex.nl.omniex.ui.app.order.overview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hannesdorfmann.mosby3.PresenterManager.getPresenter
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.order.OrderOverview
import omniex.nl.omniex.ui.adapters.CartAdapter
import omniex.nl.omniex.ui.app.order.OrderActivity
import omniex.nl.omniex.ui.base.BaseFragment

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import javax.inject.Inject



@EFragment(R.layout.fragment_order_overview)
open class OrderOverviewFragment : BaseFragment<OrderOverviewView, OrderOverviewPresenter>(), OrderOverviewView {

    @ViewById(R.id.order_overview_rv)
    lateinit var mCartRv: RecyclerView
    @ViewById(R.id.order_overview_cart_cost)
    lateinit var mCartCostTv: TextView
    @ViewById(R.id.order_overview_shipping_cost)
    lateinit var mShippingCostTv: TextView
    @ViewById(R.id.order_overview_total_cost)
    lateinit var mTotalCostTv: TextView
    @ViewById(R.id.order_overview_payment_method)
    lateinit var mPaymentMethodTv: TextView
    @ViewById(R.id.order_overview_shipping_address_name)
    lateinit var mShippingAddressNameTv: TextView
    @ViewById(R.id.order_overview_shipping_address_company)
    lateinit var mShippingAddressCompanyTv: TextView
    @ViewById(R.id.order_overview_shipping_address_street)
    lateinit var mShippingAddressStreetTv: TextView
    @ViewById(R.id.order_overview_shipping_address_code_city)
    lateinit var mShippingAddressCodeCityTv: TextView
    @ViewById(R.id.order_overview_payment_address_name)
    lateinit var mPaymentAddressNameTv: TextView
    @ViewById(R.id.order_overview_payment_address_company)
    lateinit var mPaymentAddressCompanyTv: TextView
    @ViewById(R.id.order_overview_payment_address_street)
    lateinit var mPaymentAddressStreetTv: TextView
    @ViewById(R.id.order_overview_payment_address_code_city)
    lateinit var mPaymentAddressCodeCityTv: TextView

    @Inject
    lateinit var mCartAdapter: CartAdapter

    private var mOrderOverview: OrderOverview? = null


    @AfterViews
    internal fun setOrderOverviewDetails() {
        mCartAdapter!!.setFromOverview(true)
        mOrderOverview = (getActivity() as OrderActivity).orderOverview
        if (mOrderOverview != null) {
            initCart()
            mCartCostTv!!.text = mOrderOverview!!.cart!!.total
            mShippingCostTv!!.text = mOrderOverview!!.shippingMethod!!.weight!!.quote!!.weightCode!!.priceText
            mTotalCostTv!!.text = mOrderOverview!!.cart!!.total//TODO fix total price
            mPaymentMethodTv!!.text = mOrderOverview!!.paymentMethod

            mShippingAddressNameTv!!.text = mOrderOverview!!.shippingAddress!!.firstName+ " " + mOrderOverview!!.shippingAddress!!.lastName
            mShippingAddressCompanyTv!!.text = mOrderOverview!!.shippingAddress!!.companyName
            mShippingAddressStreetTv!!.text = mOrderOverview!!.shippingAddress!!.addressOne+ " " + mOrderOverview!!.shippingAddress!!.addressTwo
            mShippingAddressCodeCityTv!!.text = mOrderOverview!!.shippingAddress!!.postcode+ ", " + mOrderOverview!!.shippingAddress!!.city

            mPaymentAddressNameTv!!.text = mOrderOverview!!.paymentAddress!!.firstName+ " " + mOrderOverview!!.paymentAddress!!.lastName
            mPaymentAddressCompanyTv!!.text = mOrderOverview!!.paymentAddress!!.companyName
            mPaymentAddressStreetTv!!.text = mOrderOverview!!.paymentAddress!!.addressOne+ " " + mOrderOverview!!.paymentAddress!!.addressTwo
            mPaymentAddressCodeCityTv!!.text = mOrderOverview!!.paymentAddress!!.postcode+ ", " + mOrderOverview!!.paymentAddress!!.city
        }
    }

    @Click(R.id.order_overview_cancel_btn, R.id.order_overview_confirm_btn)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.order_overview_cancel_btn -> getActivity().finish()
            R.id.order_overview_confirm_btn -> getPresenter().confirmOrder()
        }
    }

    private fun initCart() {
        mCartRv!!.adapter = mCartAdapter
        mCartAdapter!!.setItems(mOrderOverview!!.cart!!.cartProducts!!)
    }

    override fun onOrderConfirmed() {
        goToFragment(OrderCompleteFragment_.builder().build(), true)
    }

    override fun onOrderConfirmedFailed() {
        getActivity()!!.finish()
    }
}

