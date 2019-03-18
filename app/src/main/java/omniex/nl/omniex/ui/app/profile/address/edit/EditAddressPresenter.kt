package omniex.nl.omniex.ui.app.profile.address.edit

import omniex.nl.omniex.data.model.address.AddAddress
import omniex.nl.omniex.data.repository.AddressRepository
import omniex.nl.omniex.data.repository.CountriesRepository
import omniex.nl.omniex.data.repository.ShippingRepository
import omniex.nl.omniex.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class EditAddressPresenter @Inject
internal constructor(private val mAddressRepository: AddressRepository, private val mCountriesRepository: CountriesRepository, private val mShippingRepository: ShippingRepository) : BasePresenter<EditAddressView>() {

    internal fun saveAddress(addAddress: AddAddress) {
        addDisposable(mAddressRepository
                .addNewAddress(addAddress)
                .subscribe({ voidResponse ->
                    if (voidResponse.isSuccessful() && voidResponse.code() === 200)
                        ifViewAttached{it.onAddressSaved()}
                }, {it.printStackTrace()}))
    }

    internal fun editAddress(addressId: Int?, addAddress: AddAddress) {
        addDisposable(mAddressRepository
                .editAddress(addressId, addAddress)
                .subscribe(
                        { voidResponse ->
                            if (voidResponse.isSuccessful() && voidResponse.code() === 200)
                                ifViewAttached{it.onAddressSaved()}
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

    internal fun getCountries() {
        addDisposable(mCountriesRepository
                .countries
                .subscribe(
                        { countryResponse ->
                            if (countryResponse.isSuccessful() && countryResponse.code() === 200)
                                ifViewAttached { view -> view.onCountriesFetched(ArrayList(countryResponse.body()!!.countryList)) }
                        }, {it.printStackTrace()}))
    }

    internal fun getZones(countryId: Int?) {
        addDisposable(mCountriesRepository
                .getZones(countryId)
                .subscribe(
                        { zonesResponse ->
                            if (zonesResponse.isSuccessful() && zonesResponse.code() === 200)
                                ifViewAttached { view -> view.onZonesFetched(ArrayList(zonesResponse.body()!!.zone!!.provinceList)) }
                        }, {it.printStackTrace()}))
    }
}
