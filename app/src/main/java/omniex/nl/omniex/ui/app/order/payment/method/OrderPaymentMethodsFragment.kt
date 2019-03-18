package omniex.nl.omniex.ui.app.order.payment.method

import android.support.v7.widget.CardView
import android.view.View
import android.widget.Button

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import nl.omniex.omniexshopping.R
import nl.omniex.omniexshopping.data.model.payment.PaymentMethodSetter
import nl.omniex.omniexshopping.ui.app.order.OrderActivity
import nl.omniex.omniexshopping.ui.app.order.overview.OrderOverviewFragment_
import nl.omniex.omniexshopping.ui.base.BaseFragment

@EFragment(R.layout.fragment_order_payment_methods)
class OrderPaymentMethodsFragment : BaseFragment<OrderPaymentMethodsView, OrderPaymentMethodsPresenter>(), OrderPaymentMethodsView {

    @ViewById(R.id.payment_method_bank_cd)
    internal var mBankCd: CardView? = null

    @ViewById(R.id.payment_method_paypal_cd)
    internal var mPaypalCd: CardView? = null

    @ViewById(R.id.payment_method_next_btn)
    internal var mNextBtn: Button? = null

    private var mSelectedMethod: PaymentMethodSetter? = null
    private var mPaymentMethod: String? = null
    private var mOrderActivity: OrderActivity? = null

    fun onResume() {
        super.onResume()
        mOrderActivity = getActivity() as OrderActivity
    }

    @AfterViews
    internal fun getPaymentMethods() {
        getPresenter().getPaymentMethods()
    }

    @Click(R.id.payment_method_bank_cd, R.id.payment_method_paypal_cd)
    internal fun onMethodClick(v: View) {
        if (v.id == R.id.payment_method_bank_cd) {
            mSelectedMethod = PaymentMethodSetter("bank_transfer", "1", "")
            mBankCd!!.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryLight))
            mPaypalCd!!.setCardBackgroundColor(getResources().getColor(R.color.icons))
            mPaymentMethod = "Bank transfer"
        } else if (v.id == R.id.payment_method_paypal_cd) {
            mSelectedMethod = PaymentMethodSetter("pp_standard", "1", "")
            mPaypalCd!!.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryLight))
            mBankCd!!.setCardBackgroundColor(getResources().getColor(R.color.icons))
            mPaymentMethod = "PayPal"
        }
        mNextBtn!!.alpha = 1f
    }

    @Click(R.id.payment_method_next_btn)
    internal fun onBtnNextClick() {
        if (mSelectedMethod != null)
            getPresenter().setPaymentMethods(mSelectedMethod)
    }

    override fun onPaymentMethodSet() {
        mOrderActivity!!.setPaymentMethod(mPaymentMethod)
        goToFragment(OrderOverviewFragment_.builder().build(), true)
    }
}
