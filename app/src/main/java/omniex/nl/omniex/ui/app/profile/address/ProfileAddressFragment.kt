package omniex.nl.omniex.ui.app.profile.address

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.hannesdorfmann.mosby3.PresenterManager.getPresenter
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.ui.adapters.AddressesAdapter
import omniex.nl.omniex.ui.base.BaseFragment

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import java.util.ArrayList

import javax.inject.Inject

@EFragment(R.layout.fragment_profile_address)
open class ProfileAddressFragment : BaseFragment<ProfileAddressView, ProfileAddressPresenter>(), ProfileAddressView, AddressesAdapter.OnAddressListItemClickListener {

    @ViewById(R.id.profile_address_list_rv)
    lateinit var mAddressListRv: RecyclerView

    @ViewById(R.id.profile_address_add_btn)
    lateinit var mAddAddressBtn: Button

    @ViewById(R.id.profile_address_empty_fl)
    lateinit var mEmptyAddressListFl: FrameLayout

    @Inject
    lateinit var mAddressesAdapter: AddressesAdapter

    override fun onResume() {
        super.onResume()
        if (mAddressesAdapter != null)
            refreshList()
    }

    @AfterViews
    internal fun initAddressList() {
        mAddressesAdapter!!.setOnAddressListItemClickListener(this)
        mAddressListRv!!.adapter = mAddressesAdapter
        getPresenter().getAddressList()
    }

    private fun refreshList() {
        if (mAddressesAdapter!!.getItemCount() === 1)
            mAddressesAdapter!!.setItems(ArrayList<Address>())
        getPresenter().getAddressList()
    }

    @Click(R.id.profile_address_add_btn)
    internal fun onAddAddressClick() {
        EditAddressActivity_.intent(this).mIsNewAddress(true).start()
    }

    override fun onAddressListFetched(addressList: List<Address>) {
        mEmptyAddressListFl!!.visibility = View.GONE
        mAddressListRv!!.visibility = View.VISIBLE
        mAddressesAdapter!!.setItems(addressList)
    }

    override fun onAddressRemoved() {
        refreshList()
    }

   override fun onEditClick(address: Address?) {
        EditAddressActivity_.intent(this).mAddress(address).mIsNewAddress(false).start()
    }

    override fun onRemoveClick(idToRemove: Int?) {
        getPresenter().removeAddress(idToRemove)
    }
}
