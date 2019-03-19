package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.ui.adapters.CountriesAdapter

import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById



@EViewGroup(R.layout.view_country_list_item)
open class CountryListItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.country_item_name)
    lateinit var mCountryName: TextView

    private var mOnCountrySelectedListener: CountriesAdapter.OnCountrySelectedListener? = null
    private var mCountry: Country? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(country: Country?): CountryListItemView {
        mCountry = country
        mCountryName!!.setText(mCountry!!.name + ", " + mCountry!!.isoCode)
        return this
    }

    fun setOnCountrySelectedListener(onCountrySelectedListener: CountriesAdapter.OnCountrySelectedListener) {
        mOnCountrySelectedListener = onCountrySelectedListener
    }

    @Click(R.id.country_item_layout)
    internal fun onCountryClick() {
        mOnCountrySelectedListener!!.onCountrySelected(mCountry)
    }
}
