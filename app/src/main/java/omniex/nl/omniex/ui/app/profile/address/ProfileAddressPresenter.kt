package omniex.nl.omniex.ui.app.profile.address

import omniex.nl.omniex.data.repository.AddressRepository
import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject


class ProfileAddressPresenter @Inject
internal constructor(private val mAddressRepository: AddressRepository) : BasePresenter<ProfileAddressView>() {

    internal fun getAddressList() {
        addDisposable(mAddressRepository
                .addressList()
                .subscribe(
                        { addressesResponse ->
                            if (addressesResponse.isSuccessful() && addressesResponse.code() === 200) {
                                ifViewAttached { view -> view.onAddressListFetched(addressesResponse.body()!!.addressWrapper!!.addressList!!) }
                            }
                        }, {it.printStackTrace()}))
    }

    internal fun removeAddress(addressId: Int?) {
        addDisposable(mAddressRepository
                .removeAddress(addressId)
                .subscribe(
                        { voidResponse ->
                            if (voidResponse.isSuccessful() && voidResponse.code() === 200)
                                ifViewAttached{it.onAddressRemoved()}
                        }, {it.printStackTrace()}))
    }
}
