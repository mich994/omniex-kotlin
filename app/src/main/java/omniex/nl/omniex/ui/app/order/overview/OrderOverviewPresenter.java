package omniex.nl.omniex.ui.app.order.overview;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.omniex.omniexshopping.data.repository.OrderRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import retrofit2.Response;
import timber.log.Timber;

public class OrderOverviewPresenter extends BasePresenter<OrderOverviewView> {

    private OrderRepository mOrderRepository;

    @Inject
    OrderOverviewPresenter(OrderRepository orderRepository) {
        mOrderRepository = orderRepository;
    }

    void confirmOrder() {
        addDisposable(mOrderRepository
                .getOrderOverview()
                .flatMap(this::confirmOrder)
                .subscribe(voidResponse -> {
                    if (voidResponse.code() == 200)
                        ifViewAttached(view -> view.onOrderConfirmed());
                    else ifViewAttached(view -> view.onOrderConfirmedFailed());
                }, error -> {
                    ifViewAttached(view -> view.onOrderConfirmedFailed());
                    Timber.e(error);
                }));
    }

    Single<Response<Void>> confirmOrder(Response<Void> postOrderResponse) {
        return Single.defer(() -> {
            if (postOrderResponse.code() == 200)
                return mOrderRepository.confirmOrder();
            else
                return null;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
