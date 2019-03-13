package omniex.nl.omniex.ui.app.profile.address.edit;

import java.util.ArrayList;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.address.AddAddress;
import nl.omniex.omniexshopping.data.repository.AddressRepository;
import nl.omniex.omniexshopping.data.repository.CountriesRepository;
import nl.omniex.omniexshopping.data.repository.ShippingRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class EditAddressPresenter extends BasePresenter<EditAddressView> {

    private AddressRepository mAddressRepository;
    private CountriesRepository mCountriesRepository;
    private ShippingRepository mShippingRepository;

    @Inject
    EditAddressPresenter(AddressRepository addressRepository, CountriesRepository countriesRepository, ShippingRepository shippingRepository) {
        mAddressRepository = addressRepository;
        mCountriesRepository = countriesRepository;
        mShippingRepository = shippingRepository;
    }

    void saveAddress(AddAddress addAddress) {
        addDisposable(mAddressRepository
                .addNewAddress(addAddress)
                .subscribe(voidResponse -> {
                    if (voidResponse.isSuccessful() && voidResponse.code() == 200)
                        ifViewAttached(EditAddressView::onAddressSaved);
                }, Throwable::printStackTrace));
    }

    void editAddress(Integer addressId, AddAddress addAddress) {
        addDisposable(mAddressRepository
                .editAddress(addressId, addAddress)
                .subscribe(
                        voidResponse -> {
                            if (voidResponse.isSuccessful() && voidResponse.code() == 200)
                                ifViewAttached(EditAddressView::onAddressSaved);
                        }, Throwable::printStackTrace));
    }

    void removeAddress(Integer addressId) {
        addDisposable(mAddressRepository
                .removeAddress(addressId)
                .subscribe(
                        voidResponse -> {
                            if (voidResponse.isSuccessful() && voidResponse.code() == 200)
                                ifViewAttached(EditAddressView::onAddressRemoved);
                        }, Throwable::printStackTrace));
    }

    void getCountries() {
        addDisposable(mCountriesRepository
                .getCountries()
                .subscribe(
                        countryResponse -> {
                            if (countryResponse.isSuccessful() && countryResponse.code() == 200)
                                ifViewAttached(view -> view.onCountriesFetched(new ArrayList<>(countryResponse.body().getCountryList())));
                        }, Throwable::printStackTrace));
    }

    void getZones(Integer countryId) {
        addDisposable(mCountriesRepository
                .getZones(countryId)
                .subscribe(
                        zonesResponse -> {
                            if (zonesResponse.isSuccessful() && zonesResponse.code() == 200)
                                ifViewAttached(view -> view.onZonesFetched(new ArrayList<>(zonesResponse.body().getZone().getProvinceList())));
                        }, Throwable::printStackTrace));
    }
}
