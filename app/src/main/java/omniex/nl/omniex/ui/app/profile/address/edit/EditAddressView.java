package omniex.nl.omniex.ui.app.profile.address.edit;

import java.util.ArrayList;

import nl.omniex.omniexshopping.data.model.address.Country;
import nl.omniex.omniexshopping.data.model.address.Province;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface EditAddressView extends BaseView {

    void onCountriesFetched(ArrayList<Country> countries);

    void onZonesFetched(ArrayList<Province> zones);

    void onAddressRemoved();

    void onAddressSaved();
}
