package omniex.nl.omniex.ui.app.order.payment.method;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.payment.PaymentMethodSetter;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity;
import nl.omniex.omniexshopping.ui.app.order.overview.OrderOverviewFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_order_payment_methods)
public class OrderPaymentMethodsFragment extends BaseFragment<OrderPaymentMethodsView, OrderPaymentMethodsPresenter> implements OrderPaymentMethodsView {

    @ViewById(R.id.payment_method_bank_cd)
    CardView mBankCd;

    @ViewById(R.id.payment_method_paypal_cd)
    CardView mPaypalCd;

    @ViewById(R.id.payment_method_next_btn)
    Button mNextBtn;

    private PaymentMethodSetter mSelectedMethod;
    private String mPaymentMethod;
    private OrderActivity mOrderActivity;

    @Override
    public void onResume() {
        super.onResume();
        mOrderActivity = (OrderActivity) getActivity();
    }

    @AfterViews
    void getPaymentMethods(){
        getPresenter().getPaymentMethods();
    }

    @Click({R.id.payment_method_bank_cd, R.id.payment_method_paypal_cd})
    void onMethodClick(View v) {
        if (v.getId() == R.id.payment_method_bank_cd) {
            mSelectedMethod = new PaymentMethodSetter("bank_transfer", "1", "");
            mBankCd.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            mPaypalCd.setCardBackgroundColor(getResources().getColor(R.color.icons));
            mPaymentMethod = "Bank transfer";
        } else if (v.getId() == R.id.payment_method_paypal_cd) {
            mSelectedMethod = new PaymentMethodSetter("pp_standard", "1", "");
            mPaypalCd.setCardBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
            mBankCd.setCardBackgroundColor(getResources().getColor(R.color.icons));
            mPaymentMethod = "PayPal";
        }
        mNextBtn.setAlpha(1f);
    }

    @Click(R.id.payment_method_next_btn)
    void onBtnNextClick() {
        if (mSelectedMethod != null)
            getPresenter().setPaymentMethods(mSelectedMethod);
    }

    @Override
    public void onPaymentMethodSet() {
        mOrderActivity.setPaymentMethod(mPaymentMethod);
        goToFragment(OrderOverviewFragment_.builder().build(), true);
    }
}
