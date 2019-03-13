package omniex.nl.omniex.ui.app.profile.address;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.AddressRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class ProfileAddressPresenter extends BasePresenter<ProfileAddressView> {

    private AddressRepository mAddressRepository;

    @Inject
    ProfileAddressPresenter(AddressRepository addressRepository) {
        mAddressRepository = addressRepository;
    }

    void getAddressList() {
        addDisposable(mAddressRepository
                .getAddressList()
                .subscribe(
                        addressesResponse -> {
                            if (addressesResponse.isSuccessful() && addressesResponse.code() == 200) {
                                ifViewAttached(view -> view.onAddressListFetched(new ArrayList<>(new HashSet<>(addressesResponse.body().getAddressWrapper().getAddressList()))));
                            }
                        }, Throwable::printStackTrace));
    }

    void removeAddress(Integer addressId) {
        addDisposable(mAddressRepository
                .removeAddress(addressId)
                .subscribe(
                        voidResponse -> {
                            if (voidResponse.isSuccessful() && voidResponse.code() == 200)
                                ifViewAttached(ProfileAddressView::onAddressRemoved);
                        }, Throwable::printStackTrace));
    }
}
