package omniex.nl.omniex.ui.app.order.shipping

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.repository.AddressRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class OrderShippingPresenter @Inject
internal constructor(private val mAddressRepository: AddressRepository) : BasePresenter<OrderShippingView>() {

    internal fun getShippingAddresses() {
        addDisposable(mAddressRepository
                .shippingAddresses()
                .subscribe(
                        { shippingAddressesResponse -> ifViewAttached { view -> view.onShippingAddressesFetched(shippingAddressesResponse.body()!!.shippingAddressList!!.addressList!!) } },
                        { error ->
                            ifViewAttached({ it.onGetShippingAddressesFailed() })
                            Timber.e(error)
                        }))
    }

    internal fun setShippingAddress(existingAddress: Address) {
        addDisposable(mAddressRepository
                .setExistingShippingAddress(existingAddress)
                .subscribe({ voidResponse -> ifViewAttached { view -> view.onShippingAddressSet() } }, {it.printStackTrace()}))
    }
}
