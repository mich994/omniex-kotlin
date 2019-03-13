package omniex.nl.omniex.ui.app.profile.address;

import java.util.List;

import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface ProfileAddressView extends BaseView {
    void onAddressListFetched(List<Address> addressList);
    void onAddressRemoved();
}
