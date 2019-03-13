package omniex.nl.omniex.ui.app.order.shipping.method;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod;
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethodSetter;
import nl.omniex.omniexshopping.ui.adapters.ShippingMethodsAdapter;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity;
import nl.omniex.omniexshopping.ui.app.order.payment.method.OrderPaymentMethodsFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter;

@EFragment(R.layout.fragment_order_shipping)
public class OrderShippingMethodFragment extends BaseFragment<OrderShippingMethodView, OrderShippingMethodPresenter> implements OrderShippingMethodView, BaseRecyclerAdapter.ItemClickListener<ShippingMethod> {

    @ViewById(R.id.order_shipping_rv)
    RecyclerView mShippingAddressRv;

    @ViewById(R.id.order_shipping_empty_tv)
    TextView mEmptyListTv;

    @ViewById(R.id.order_shipping_next_btn)
    Button mNextBtn;

    @Inject
    ShippingMethodsAdapter mShippingMethodsAdapter;

    private ShippingMethod mSelectedMethod;
    private OrderActivity mOrderActivity;

    @Override
    public void onResume() {
        super.onResume();
        mOrderActivity = (OrderActivity) getActivity();
    }

    @AfterViews
    void initShippingMethodsList(){
        mShippingMethodsAdapter.setItemClickListener(this);
        mShippingAddressRv.setAdapter(mShippingMethodsAdapter);
        getPresenter().getShippingMethods();
    }

    @Click(R.id.order_shipping_next_btn)
    void onClick(){
        if(mSelectedMethod!=null)
            getPresenter().setShippingMethod(new ShippingMethodSetter(mSelectedMethod.getWeight().getQuote().getWeightCode().getCode(),""));
    }

    @Override
    public void onShippingMethodsFetched(List<ShippingMethod> shippingMethods) {
        mShippingMethodsAdapter.setItems(shippingMethods);
    }

    @Override
    public void onShippingMethodSet() {
        mOrderActivity.setShippingMethod(mSelectedMethod);
        goToFragment(OrderPaymentMethodsFragment_.builder().build(), true);
    }

    @Override
    public void onItemClick(ShippingMethod shippingMethod) {
        mSelectedMethod = shippingMethod;
        mShippingMethodsAdapter.setSelection(mShippingMethodsAdapter.getItems().indexOf(shippingMethod));
        mNextBtn.setAlpha(1f);
    }
}
