package omniex.nl.omniex.ui.app.profile.order;

import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.order.OrderStatus;
import nl.omniex.omniexshopping.ui.adapters.OrderStatutesAdapter;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_profile_order)
public class ProfileOrderFragment extends BaseFragment<ProfileOrderView, ProfileOrderPresenter> implements ProfileOrderView {

    @ViewById(R.id.profile_order_list_rv)
    RecyclerView mOrdersRv;

    @Inject
    OrderStatutesAdapter mOrderStatutesAdapter;

    @AfterViews
    void initOrdersList() {
        mOrdersRv.setAdapter(mOrderStatutesAdapter);
            getPresenter().getOrderStatuses();
    }

    @Override
    public void onOrderStatusesFetched(List<OrderStatus> orderStatusList) {
        mOrderStatutesAdapter.setItems(orderStatusList);
    }
}
