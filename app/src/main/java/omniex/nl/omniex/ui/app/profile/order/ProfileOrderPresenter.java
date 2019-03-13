package omniex.nl.omniex.ui.app.profile.order;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.OrderRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class ProfileOrderPresenter extends BasePresenter<ProfileOrderView> {

    private OrderRepository mOrderRepository;

    @Inject
    ProfileOrderPresenter(OrderRepository orderRepository) {
        mOrderRepository = orderRepository;
    }

    void getOrderStatuses() {
        addDisposable(mOrderRepository
                .getOrderStatuses()
                .subscribe(
                        orderStatusesResponse -> {
                            if(orderStatusesResponse.code()==200)
                                ifViewAttached(view -> view.onOrderStatusesFetched(orderStatusesResponse.body().getOrderStatusList()));
                            }, Throwable::printStackTrace));
    }

}
