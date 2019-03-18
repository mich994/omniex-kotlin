package omniex.nl.omniex.ui.app.profile.address.edit

import android.widget.EditText
import android.widget.TextView

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra
import org.androidannotations.annotations.ViewById

import java.util.ArrayList

import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.AddAddress
import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.adapters.CountriesAdapter
import omniex.nl.omniex.ui.adapters.ZonesAdapter
import omniex.nl.omniex.ui.base.BaseActivity


@EActivity(R.layout.activity_edit_address)
open class EditAddressActivity : BaseActivity<EditAddressView, EditAddressPresenter>(), EditAddressView, CountriesAdapter.OnCountrySelectedListener, ZonesAdapter.OnZoneSelectedClickListener {

    @ViewById(R.id.edit_address_input_first_name)
    internal var mInputFirstName: EditText? = null

    @ViewById(R.id.edit_address_input_last_name)
    internal var mInputLastName: EditText? = null

    @ViewById(R.id.edit_address_input_company_name)
    internal var mInputCompanyName: EditText? = null

    @ViewById(R.id.edit_address_input_company_address)
    internal var mInputCompanyAddress: EditText? = null

    @ViewById(R.id.edit_address_input_city)
    internal var mInputCity: EditText? = null

    @ViewById(R.id.edit_address_input_post_code)
    internal var mInputPostCode: EditText? = null

    @ViewById(R.id.edit_address_country_tv)
    internal var mCountry: TextView? = null

    @ViewById(R.id.edit_address_zone_tv)
    internal var mZone: TextView? = null

    @Extra
    internal var mIsNewAddress: Boolean? = null

    @Extra
    internal var mAddress: Address? = null

    private var mSelectedCountry: Country? = null
    private var mSelectedZone: Province? = null

    protected override fun onFirstCreate() {
        super.onFirstCreate()
        setToolbar()
    }

    private fun setToolbar() {
        customToolbar
                .setIconStart(R.drawable.twotone_arrow_back_black_36)
                .setIconStarClickListener(IconStartClickListener { this.finish() })
        if ((!mIsNewAddress)!!) {
            customToolbar
                    .setIconEnd(R.drawable.baseline_delete_outline_black_36)
                    .setIconEndClickListener({ getPresenter().removeAddress(Integer.valueOf(mAddress!!.getAddressId())) })
        }
    }

    @AfterViews
    internal fun setupDetails() {
        if ((mIsNewAddress)!! && mAddress != null) {
            mInputFirstName!!.setText(mAddress!!.getFirstName())
            mInputLastName!!.setText(mAddress!!.getLastName())
            mInputCompanyName!!.setText(mAddress!!.getCompanyName())
            mInputCompanyAddress!!.setText(mAddress!!.getAddressOne())
            mInputCity!!.setText(mAddress!!.getCity())
            mCountry!!.setText(mAddress!!.getCountryNameFormated())
            mZone!!.setText(mAddress!!.getZoneNameFormated())
            mInputPostCode!!.setText(mAddress!!.getPostcode())
            mSelectedCountry = Country(Integer.valueOf(mAddress!!.getCountryId()), mAddress!!.getCountry(), mAddress!!.getIsoCodeTwo())
            mSelectedZone = Province(mAddress!!.getZoneId(), mAddress!!.getCountryId(), mAddress!!.getZoneName(), mAddress!!.getZoneCode())
        }
        setupCountryZoneListeners()
    }

    private fun setupCountryZoneListeners() {
        mCountry!!.setOnClickListener { v -> getPresenter().getCountries() }
        mZone!!.setOnClickListener { v ->
            if (mSelectedCountry != null)
                getPresenter().getZones(mSelectedCountry!!.countryId)
        }
    }

    @Click(R.id.edit_address_save_btn)
    internal fun onSaveAddressClick() {
        val addAddress = AddAddress(mInputFirstName!!.text.toString(),
                mInputLastName!!.text.toString(),
                mInputCity!!.text.toString(),
                mInputCompanyAddress!!.text.toString(),
                "",
                mSelectedCountry!!.countryId.toString(),
                mInputPostCode!!.text.toString(),
                mSelectedZone!!.zoneId,
                mInputCompanyName!!.text.toString())
        if (mIsNewAddress!!)
            getPresenter().saveAddress(addAddress)
        else
            getPresenter().editAddress(Integer.valueOf(mAddress!!.getAddressId()), addAddress)
    }

    override fun onCountriesFetched(countries: ArrayList<Country>) {
        CountryDialog_.builder().mCountries(countries).build().setOnCountrySelectedListener(this).show(supportFragmentManager, "")
        mZone!!.text = ""
    }

    override fun onZonesFetched(zones: ArrayList<Province>) {
        ZoneDialog_.builder().mProvinces(zones).build().setOnZoneSelectedClickListener(this).show(supportFragmentManager, "")
    }

    override fun onAddressSaved() {
        finish()
    }

    override fun onAddressRemoved() {
        finish()
    }

    override fun onCountrySelected(country: Country?) {
        mCountry!!.text = country!!.fullName
        mSelectedCountry = country
    }

    override fun onZoneSelected(province: Province?) {
        mZone!!.text = province!!.fullName
        mSelectedZone = province
    }
}
