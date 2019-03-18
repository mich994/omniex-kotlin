package omniex.nl.omniex.ui.app.profile.address.edit

import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.base.BaseView
import java.util.ArrayList

interface EditAddressView : BaseView {

    fun onCountriesFetched(countries: ArrayList<Country>)

    fun onZonesFetched(zones: ArrayList<Province>)

    fun onAddressRemoved()

    fun onAddressSaved()
}
