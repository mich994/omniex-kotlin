package omniex.nl.omniex.utils

import omniex.nl.omniex.data.model.address.Address
import java.util.*


class AddressUtils {

    fun removeDuplicates(pAddressList: List<Address>): List<Address> {
        return if (pAddressList.size == 1)
            pAddressList
        else {
            ArrayList(HashSet(pAddressList))
        }
    }
}
