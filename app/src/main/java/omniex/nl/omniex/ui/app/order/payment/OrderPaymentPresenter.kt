package omniex.nl.omniex.ui.app.order.payment

import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.repository.AddressRepository
import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject


class OrderPaymentPresenter @Inject
internal constructor(private val mAddressRepository: AddressRepository) : BasePresenter<OrderPaymentView>() {

    internal fun getPaymentAddresses() {
        addDisposable(mAddressRepository
                .paymentAddresses()
                .subscribe(
                        { shippingAddressesResponse -> ifViewAttached { view -> view.onPaymentAddressesFetched(shippingAddressesResponse.body()!!.shippingAddressList!!.addressList!!) } },
                        { error(it.printStackTrace()) }))
    }

    internal fun setPaymentAddress(existingAddress: Address) {
        addDisposable(mAddressRepository
                .setExistingPaymentAddress(existingAddress)
                .subscribe({ voidResponse -> ifViewAttached { view -> view.onPaymentMethodSet() } }, { error(it.printStackTrace()) }))

    }
}

