package omniex.nl.omniex.ui.app.order.overview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.order.OrderOverview;
import nl.omniex.omniexshopping.ui.adapters.CartAdapter;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity;
import nl.omniex.omniexshopping.ui.app.order.complete.OrderCompleteFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_order_overview)
public class OrderOverviewFragment extends BaseFragment<OrderOverviewView, OrderOverviewPresenter> implements OrderOverviewView{

    @ViewById(R.id.order_overview_rv)
    RecyclerView mCartRv;
    @ViewById(R.id.order_overview_cart_cost)
    TextView mCartCostTv;
    @ViewById(R.id.order_overview_shipping_cost)
    TextView mShippingCostTv;
    @ViewById(R.id.order_overview_total_cost)
    TextView mTotalCostTv;
    @ViewById(R.id.order_overview_payment_method)
    TextView mPaymentMethodTv;
    @ViewById(R.id.order_overview_shipping_address_name)
    TextView mShippingAddressNameTv;
    @ViewById(R.id.order_overview_shipping_address_company)
    TextView mShippingAddressCompanyTv;
    @ViewById(R.id.order_overview_shipping_address_street)
    TextView mShippingAddressStreetTv;
    @ViewById(R.id.order_overview_shipping_address_code_city)
    TextView mShippingAddressCodeCityTv;
    @ViewById(R.id.order_overview_payment_address_name)
    TextView mPaymentAddressNameTv;
    @ViewById(R.id.order_overview_payment_address_company)
    TextView mPaymentAddressCompanyTv;
    @ViewById(R.id.order_overview_payment_address_street)
    TextView mPaymentAddressStreetTv;
    @ViewById(R.id.order_overview_payment_address_code_city)
    TextView mPaymentAddressCodeCityTv;

    @Inject
    CartAdapter mCartAdapter;

    private OrderOverview mOrderOverview;

    @Override
    public void onResume() {
        super.onResume();
    }

    @AfterViews
    void setOrderOverviewDetails() {
        mCartAdapter.setFromOverview(true);
        mOrderOverview = ((OrderActivity) getActivity()).getOrderOverview();
        if (mOrderOverview != null) {
            initCart();
            mCartCostTv.setText(mOrderOverview.getCart().getTotal());
            mShippingCostTv.setText(mOrderOverview.getShippingMethod().getWeight().getQuote().getWeightCode().getPriceText());
            mTotalCostTv.setText(mOrderOverview.getCart().getTotal());//TODO fix total price
            mPaymentMethodTv.setText(mOrderOverview.getPaymentMethod());

            mShippingAddressNameTv.setText(mOrderOverview.getShippingAddress().getFirstName() + " " + mOrderOverview.getShippingAddress().getLastName());
            mShippingAddressCompanyTv.setText(mOrderOverview.getShippingAddress().getCompanyName());
            mShippingAddressStreetTv.setText(mOrderOverview.getShippingAddress().getAddressOne() + " " + mOrderOverview.getShippingAddress().getAddressTwo());
            mShippingAddressCodeCityTv.setText(mOrderOverview.getShippingAddress().getPostcode() + ", " + mOrderOverview.getShippingAddress().getCity());

            mPaymentAddressNameTv.setText(mOrderOverview.getPaymentAddress().getFirstName() + " " + mOrderOverview.getPaymentAddress().getLastName());
            mPaymentAddressCompanyTv.setText(mOrderOverview.getPaymentAddress().getCompanyName());
            mPaymentAddressStreetTv.setText(mOrderOverview.getPaymentAddress().getAddressOne() + " " + mOrderOverview.getPaymentAddress().getAddressTwo());
            mPaymentAddressCodeCityTv.setText(mOrderOverview.getPaymentAddress().getPostcode() + ", " + mOrderOverview.getPaymentAddress().getCity());
        }
    }

    @Click({R.id.order_overview_cancel_btn, R.id.order_overview_confirm_btn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_overview_cancel_btn:
                getActivity().finish();
                break;
            case R.id.order_overview_confirm_btn:
                getPresenter().confirmOrder();
                break;
        }
    }

    private void initCart() {
        mCartRv.setAdapter(mCartAdapter);
        mCartAdapter.setItems(mOrderOverview.getCart().getCartProducts());
    }

    @Override
    public void onOrderConfirmed() {
        goToFragment(OrderCompleteFragment_.builder().build(), true);
    }

    @Override
    public void onOrderConfirmedFailed() {
        getActivity().finish();
    }
}

