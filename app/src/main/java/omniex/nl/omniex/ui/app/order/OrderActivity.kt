package omniex.nl.omniex.ui.app.order

import com.hannesdorfmann.mosby3.PresenterManager.getPresenter
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.model.cart.Cart
import omniex.nl.omniex.data.model.order.OrderOverview
import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.ui.base.BaseActivity
import omniex.nl.omniex.ui.views.toolbar.CustomToolbar
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_order)
open class OrderActivity : BaseActivity<OrderView, OrderPresenter>(), OrderView {

    val orderOverview = OrderOverview()

    override fun onFirstCreate() {
        super.onFirstCreate()
        customToolbar.setIconStart(R.drawable.twotone_arrow_back_black_36)
                .setIconStarClickListener(object : CustomToolbar.IconStartClickListener {
                    override fun onIconStartClick() {
                        finishActivity(1)
                    }
                })
    }

    @AfterViews
    internal fun initOrderProcess() {
        getPresenter().getCart()
    }

    override fun onCartFetched(cart: Cart) {
        goToFragment(OrderShippingFragment_.builder().build(), true, "")
        orderOverview.cart = cart
    }

    fun setShippingAddress(shippingAddress: Address) {
        orderOverview.shippingAddress = shippingAddress
    }

    fun setPaymentAddress(paymentAddress: Address) {
        orderOverview.paymentAddress = paymentAddress
    }

    fun setShippingMethod(shippingMethod: ShippingMethod) {
        orderOverview.shippingMethod = shippingMethod
    }

    fun setPaymentMethod(paymentMethod: String) {
        orderOverview.paymentMethod = paymentMethod
    }

    override fun startLoading() {
        showProgressBar()
    }

    override fun stopLoading() {
        hideProgressBar()
    }

    override fun onOrderOverviewSuccess() {
        goToFragment(OrderShippingFragment_.builder().build(), true, "")
    }

    override fun onOrderOverviewFailed() {
        finish()
    }
}
