package omniex.nl.omniex.ui.app.profile.address.edit

import android.widget.EditText
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.AddAddress
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.adapters.CountriesAdapter
import omniex.nl.omniex.ui.adapters.ZonesAdapter
import omniex.nl.omniex.ui.base.BaseActivity
import omniex.nl.omniex.ui.views.dialogs.address.CountryDialog_
import omniex.nl.omniex.ui.views.dialogs.address.ZoneDialog_
import omniex.nl.omniex.ui.views.toolbar.CustomToolbar
import org.androidannotations.annotations.*
import java.util.*


@EActivity(R.layout.activity_edit_address)
open class EditAddressActivity : BaseActivity<EditAddressView, EditAddressPresenter>(), EditAddressView, CountriesAdapter.OnCountrySelectedListener, ZonesAdapter.OnZoneSelectedClickListener {

    @ViewById(R.id.edit_address_input_first_name)
    lateinit var mInputFirstName: EditText

    @ViewById(R.id.edit_address_input_last_name)
    lateinit var mInputLastName: EditText

    @ViewById(R.id.edit_address_input_company_name)
    lateinit var mInputCompanyName: EditText

    @ViewById(R.id.edit_address_input_company_address)
    lateinit var mInputCompanyAddress: EditText

    @ViewById(R.id.edit_address_input_city)
    lateinit var mInputCity: EditText

    @ViewById(R.id.edit_address_input_post_code)
    lateinit var mInputPostCode: EditText

    @ViewById(R.id.edit_address_country_tv)
    lateinit var mCountry: TextView

    @ViewById(R.id.edit_address_zone_tv)
    lateinit var mZone: TextView

    @JvmField
    @Extra
    var mIsNewAddress: Boolean? = null

    @Extra
    lateinit var mAddress: Address

    private var mSelectedCountry: Country? = null
    private var mSelectedZone: Province? = null

    override fun onFirstCreate() {
        super.onFirstCreate()
        setToolbar()
    }

    private fun setToolbar() {
        customToolbar
                .setIconStart(R.drawable.twotone_arrow_back_black_36)
                .setIconStarClickListener(object:CustomToolbar.IconStartClickListener {
                    override fun onIconStartClick() {
                        this@EditAddressActivity.finish()
                    }
                })
        if ((mIsNewAddress)!!) {
            customToolbar
                    .setIconEnd(R.drawable.baseline_delete_outline_black_36)
                    .setIconEndClickListener(object : CustomToolbar.IconEndClickListner{
                        override fun onIconEndClick() {
                            getPresenter().removeAddress(Integer.valueOf(mAddress!!.addressId))
                        }
                    })
        }
    }

    @AfterViews
    internal fun setupDetails() {
        if ((mIsNewAddress)!! && mAddress != null) {
            mInputFirstName!!.setText(mAddress!!.firstName)
            mInputLastName!!.setText(mAddress!!.lastName)
            mInputCompanyName!!.setText(mAddress!!.companyName)
            mInputCompanyAddress!!.setText(mAddress!!.addressOne)
            mInputCity!!.setText(mAddress!!.city!!)
            mCountry!!.text = mAddress!!.countryNameFormated
            mZone!!.text = mAddress!!.zoneNameFormated
            mInputPostCode!!.setText(mAddress!!.postcode)
            mSelectedCountry = Country(Integer.valueOf(mAddress!!.countryId), mAddress!!.country!!, mAddress!!.isoCodeTwo!!)
            mSelectedZone = Province(mAddress!!.zoneId!!, mAddress!!.countryId!!, mAddress!!.zoneName!!, mAddress!!.zoneCode!!)
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
            getPresenter().editAddress(Integer.valueOf(mAddress!!.addressId), addAddress)
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
