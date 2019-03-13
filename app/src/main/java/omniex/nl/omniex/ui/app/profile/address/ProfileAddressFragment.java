package omniex.nl.omniex.ui.app.profile.address;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.ui.adapters.AddressesAdapter;
import nl.omniex.omniexshopping.ui.app.profile.address.edit.EditAddressActivity_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_profile_address)
public class ProfileAddressFragment extends BaseFragment<ProfileAddressView, ProfileAddressPresenter> implements ProfileAddressView, AddressesAdapter.OnAddressListItemClickListener {

    @ViewById(R.id.profile_address_list_rv)
    RecyclerView mAddressListRv;

    @ViewById(R.id.profile_address_add_btn)
    Button mAddAddressBtn;

    @ViewById(R.id.profile_address_empty_fl)
    FrameLayout mEmptyAddressListFl;

    @Inject
    AddressesAdapter mAddressesAdapter;

    @Override
    public void onResume() {
        super.onResume();
        if (mAddressesAdapter != null)
            refreshList();
    }

    @AfterViews
    void initAddressList() {
        mAddressesAdapter.setOnAddressListItemClickListener(this);
        mAddressListRv.setAdapter(mAddressesAdapter);
        getPresenter().getAddressList();
    }

    private void refreshList() {
        if(mAddressesAdapter.getItemCount()==1)
            mAddressesAdapter.setItems(new ArrayList<Address>());
        getPresenter().getAddressList();
    }

    @Click(R.id.profile_address_add_btn)
    void onAddAddressClick() {
        EditAddressActivity_.intent(this).mIsNewAddress(true).start();
    }

    @Override
    public void onAddressListFetched(List<Address> addressList) {
        mEmptyAddressListFl.setVisibility(View.GONE);
        mAddressListRv.setVisibility(View.VISIBLE);
        mAddressesAdapter.setItems(addressList);
    }

    @Override
    public void onAddressRemoved() {
        refreshList();
    }

    @Override
    public void onEditClick(Address address) {
        EditAddressActivity_.intent(this).mAddress(address).mIsNewAddress(false).start();
    }

    @Override
    public void onRemoveClick(Integer idToRemove) {
        getPresenter().removeAddress(idToRemove);
    }
}
