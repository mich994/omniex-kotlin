package omniex.nl.omniex.ui.app.order.shipping;

import java.util.ArrayList;
import java.util.HashSet;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.data.repository.AddressRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import timber.log.Timber;

public class OrderShippingPresenter extends BasePresenter<OrderShippingView> {

    private AddressRepository mAddressRepository;

    @Inject
    OrderShippingPresenter(AddressRepository addressRepository) {
        mAddressRepository = addressRepository;
    }

    void getShippingAddresses() {
        addDisposable(mAddressRepository
                .getShippingAddresses()
                .subscribe(
                        shippingAddressesResponse -> {
                            ifViewAttached(view -> view.onShippingAddressesFetched(new ArrayList<>(new HashSet<>(shippingAddressesResponse.body().getShippingAddressList().getAddressList()))));
                        }, error->{
                            ifViewAttached(OrderShippingView::onGetShippingAddressesFailed);
                            Timber.e(error);
                        }));
    }

    void setShippingAddress(Address existingAddress) {
        addDisposable(mAddressRepository
                .setExistingShippingAddress(existingAddress)
                .subscribe(voidResponse -> {
                    ifViewAttached(view -> view.onShippingAddressSet());
                }, Throwable::printStackTrace));
    }
}
