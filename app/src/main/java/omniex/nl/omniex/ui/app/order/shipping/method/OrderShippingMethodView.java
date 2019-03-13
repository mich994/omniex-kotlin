package omniex.nl.omniex.ui.app.order.shipping.method;

import java.util.List;

import nl.omniex.omniexshopping.data.model.shipping.ShippingMethod;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface OrderShippingMethodView extends BaseView {

    void onShippingMethodsFetched(List<ShippingMethod> shippingMethods);
    void onShippingMethodSet();
}
