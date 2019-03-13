package omniex.nl.omniex.ui.app.order;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.AddressRepository;
import nl.omniex.omniexshopping.data.repository.CartRepository;
import nl.omniex.omniexshopping.data.repository.OrderRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import timber.log.Timber;

public class OrderPresenter extends BasePresenter<OrderView> {

    private OrderRepository mOrderRepository;
    private AddressRepository mAddressRepository;
    private CartRepository mCartRepository;

    @Inject
    OrderPresenter(OrderRepository orderRepository, AddressRepository addressRepository, CartRepository cartRepository) {
        mOrderRepository = orderRepository;
        mAddressRepository = addressRepository;
        mCartRepository = cartRepository;
    }

    void getCart() {
        addDisposable(mCartRepository
                .getCart()
                .subscribe(cartResponse -> {
                    if (cartResponse.code() == 200)
                        ifViewAttached(view -> view.onCartFetched(cartResponse.body().getCart()));
                }, Throwable::printStackTrace));
    }

    void postOrderOverview() {
        addDisposable(mOrderRepository
                .getOrderOverview()
                .subscribe(voidResponse -> {
                    if (voidResponse.code() == 200)
                        ifViewAttached(OrderView::onOrderOverviewSuccess);
                    else
                        ifViewAttached(OrderView::onOrderOverviewFailed);
                }, error -> {
                    ifViewAttached(OrderView::onOrderOverviewFailed);
                    Timber.e(error);
                }));
    }

}
