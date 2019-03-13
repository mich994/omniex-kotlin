package omniex.nl.omniex.ui.app.order.shipping.method;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.response.ShippingMethodResponse;
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod;
import nl.omniex.omniexshopping.data.model.shipping.ShippingMethodSetter;
import nl.omniex.omniexshopping.data.repository.ShippingRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class OrderShippingMethodPresenter extends BasePresenter<OrderShippingMethodView> {

    private ShippingRepository mShippingRepository;

    @Inject
    OrderShippingMethodPresenter(ShippingRepository shippingRepository) {
        mShippingRepository = shippingRepository;
    }

    void getShippingMethods() {
        addDisposable(mShippingRepository
                .getShippingMethods()
                .subscribe(
                        shippingMethodResponse -> {
                            if (shippingMethodResponse.code() == 200) {
                                ifViewAttached(view -> view.onShippingMethodsFetched(createList(shippingMethodResponse.body())));
                            }
                        }, Throwable::printStackTrace));
    }

    private List<ShippingMethod> createList(ShippingMethodResponse shippingMethodResponse) {
        List<ShippingMethod> shippingMethods = new ArrayList<>();
        shippingMethods.add(shippingMethodResponse.getShippingMethodWrapper().getShippingMethod());
        return shippingMethods;
    }

    void setShippingMethod(ShippingMethodSetter shippingMethodSetter) {
        addDisposable(mShippingRepository
                .setShippingMethod(shippingMethodSetter)
                .subscribe(
                        response -> {
                            if (response.code()==200)
                                ifViewAttached(view -> view.onShippingMethodSet());
                        }, Throwable::printStackTrace));
    }
}
