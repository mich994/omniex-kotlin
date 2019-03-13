package omniex.nl.omniex.ui.app.profile.address.edit;

import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.address.AddAddress;
import nl.omniex.omniexshopping.data.model.address.Address;
import nl.omniex.omniexshopping.data.model.address.Country;
import nl.omniex.omniexshopping.data.model.address.Province;
import nl.omniex.omniexshopping.ui.adapters.CountriesAdapter;
import nl.omniex.omniexshopping.ui.adapters.ZonesAdapter;
import nl.omniex.omniexshopping.ui.base.BaseActivity;
import nl.omniex.omniexshopping.ui.views.dialogs.address.CountryDialog_;
import nl.omniex.omniexshopping.ui.views.dialogs.address.ZoneDialog_;

@EActivity(R.layout.activity_edit_address)
public class EditAddressActivity extends BaseActivity<EditAddressView, EditAddressPresenter> implements EditAddressView, CountriesAdapter.OnCountrySelectedListener, ZonesAdapter.OnZoneSelectedClickListener {

    @ViewById(R.id.edit_address_input_first_name)
    EditText mInputFirstName;

    @ViewById(R.id.edit_address_input_last_name)
    EditText mInputLastName;

    @ViewById(R.id.edit_address_input_company_name)
    EditText mInputCompanyName;

    @ViewById(R.id.edit_address_input_company_address)
    EditText mInputCompanyAddress;

    @ViewById(R.id.edit_address_input_city)
    EditText mInputCity;

    @ViewById(R.id.edit_address_input_post_code)
    EditText mInputPostCode;

    @ViewById(R.id.edit_address_country_tv)
    TextView mCountry;

    @ViewById(R.id.edit_address_zone_tv)
    TextView mZone;

    @Extra
    Boolean mIsNewAddress;

    @Extra
    Address mAddress;

    private Country mSelectedCountry;
    private Province mSelectedZone;

    @Override
    protected void onFirstCreate() {
        super.onFirstCreate();
        setToolbar();
    }

    private void setToolbar() {
        getCustomToolbar()
                .setIconStart(R.drawable.twotone_arrow_back_black_36)
                .setIconStarClickListener(this::finish);
        if (!mIsNewAddress) {
            getCustomToolbar()
                    .setIconEnd(R.drawable.baseline_delete_outline_black_36)
                    .setIconEndClickListener(() -> getPresenter().removeAddress(Integer.valueOf(mAddress.getAddressId())));
        }
    }

    @AfterViews
    void setupDetails() {
        if (!mIsNewAddress && mAddress != null) {
            mInputFirstName.setText(mAddress.getFirstName());
            mInputLastName.setText(mAddress.getLastName());
            mInputCompanyName.setText(mAddress.getCompanyName());
            mInputCompanyAddress.setText(mAddress.getAddressOne());
            mInputCity.setText(mAddress.getCity());
            mCountry.setText(mAddress.getCountryNameFormated());
            mZone.setText(mAddress.getZoneNameFormated());
            mInputPostCode.setText(mAddress.getPostcode());
            mSelectedCountry = new Country(Integer.valueOf(mAddress.getCountryId()), mAddress.getCountry(), mAddress.getIsoCodeTwo());
            mSelectedZone = new Province(mAddress.getZoneId(), mAddress.getCountryId(), mAddress.getZoneName(), mAddress.getZoneCode());
        }
        setupCountryZoneListeners();
    }

    private void setupCountryZoneListeners() {
        mCountry.setOnClickListener(v -> getPresenter().getCountries());
        mZone.setOnClickListener(v -> {
            if (mSelectedCountry != null)
                getPresenter().getZones(mSelectedCountry.getCountryId());
        });
    }

    @Click(R.id.edit_address_save_btn)
    void onSaveAddressClick() {
        AddAddress addAddress = new AddAddress(mInputFirstName.getText().toString(),
                mInputLastName.getText().toString(),
                mInputCity.getText().toString(),
                mInputCompanyAddress.getText().toString(),
                "",
                String.valueOf(mSelectedCountry.getCountryId()),
                mInputPostCode.getText().toString(),
                mSelectedZone.getZoneId(),
                mInputCompanyName.getText().toString());
        if (mIsNewAddress)
            getPresenter().saveAddress(addAddress);
        else
            getPresenter().editAddress(Integer.valueOf(mAddress.getAddressId()), addAddress);
    }

    @Override
    public void onCountriesFetched(ArrayList<Country> countries) {
        CountryDialog_.builder().mCountries(countries).build().setOnCountrySelectedListener(this).show(getSupportFragmentManager(), "");
        mZone.setText("");
    }

    @Override
    public void onZonesFetched(ArrayList<Province> zones) {
        ZoneDialog_.builder().mProvinces(zones).build().setOnZoneSelectedClickListener(this).show(getSupportFragmentManager(), "");
    }

    @Override
    public void onAddressSaved() {
        finish();
    }

    @Override
    public void onAddressRemoved() {
        finish();
    }

    @Override
    public void onCountrySelected(Country country) {
        mCountry.setText(country.getFullName());
        mSelectedCountry = country;
    }

    @Override
    public void onZoneSelected(Province province) {
        mZone.setText(province.getFullName());
        mSelectedZone = province;
    }
}
