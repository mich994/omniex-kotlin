package omniex.nl.omniex.ui.app.order.shipping;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.ui.adapters.OrderAddressesAdapter;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity;
import nl.omniex.omniexshopping.ui.app.order.payment.OrderPaymentFragment_;
import nl.omniex.omniexshopping.ui.app.profile.address.edit.EditAddressActivity_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter;

@EFragment(R.layout.fragment_order_shipping)
public class OrderShippingFragment extends BaseFragment<OrderShippingView, OrderShippingPresenter> implements OrderShippingView, BaseRecyclerAdapter.ItemClickListener<Address> {

    @ViewById(R.id.order_shipping_rv)
    RecyclerView mShippingAddressRv;

    @ViewById(R.id.order_shipping_empty_tv)
    TextView mEmptyListTv;

    @ViewById(R.id.order_shipping_next_btn)
    Button mNextBtn;

    @Inject
    OrderAddressesAdapter mOrderAddressesAdapter;

    private String mSelectedAddressId;
    private Address mSelectedAddress;
    private OrderActivity mOrderActivity;

    @Override
    public void onResume() {
        super.onResume();
        if (mOrderAddressesAdapter != null)
            getPresenter().getShippingAddresses();
        mOrderActivity = (OrderActivity) getActivity();
    }

    @AfterViews
    void initAddressList() {
        mOrderAddressesAdapter.resetSelection();
        mOrderAddressesAdapter.setItemClickListener(this);
        mShippingAddressRv.setAdapter(mOrderAddressesAdapter);
    }

    @Click({R.id.order_shipping_empty_tv, R.id.order_shipping_next_btn})
    void onClick(View v) {
        if (v.getId() == R.id.order_shipping_empty_tv)
            EditAddressActivity_.intent(getActivity()).mIsNewAddress(true).start();
        else if (v.getId() == R.id.order_shipping_next_btn) {
            if (mSelectedAddress != null)
                getPresenter().setShippingAddress(mSelectedAddress);
        }
    }

    @Override
    public void onShippingAddressesFetched(List<Address> addressList) {
        mOrderAddressesAdapter.setItems(addressList);
        mEmptyListTv.setVisibility(View.GONE);
    }

    @Override
    public void onGetShippingAddressesFailed() {
        mEmptyListTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShippingAddressSet() {
        mOrderActivity.setShippingAddress(mSelectedAddress);
        goToFragment(OrderPaymentFragment_.builder().build(),true);
    }

    @Override
    public void onItemClick(Address address) {
        mOrderAddressesAdapter.setSelected(mOrderAddressesAdapter.getItems().indexOf(address));
        mSelectedAddress =address;
        mNextBtn.setAlpha(1f);
    }
}
