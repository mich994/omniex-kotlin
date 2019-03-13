package omniex.nl.omniex.ui.app.order.payment;

import java.util.List;

import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface OrderPaymentView extends BaseView {

    void onPaymentAddressesFetched(List<Address> addressList);

    void onPaymentMethodSet();
}
