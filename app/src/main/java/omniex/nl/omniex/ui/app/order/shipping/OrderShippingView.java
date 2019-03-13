package omniex.nl.omniex.ui.app.order.shipping;

import java.util.List;

import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface OrderShippingView extends BaseView {

    void onShippingAddressesFetched(List<Address> addressList);
    void onGetShippingAddressesFailed();
    void onShippingAddressSet();
}
