package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.CountryListItemView

@EBean
class CountriesAdapter : BaseRecyclerAdapter<Country, CountryListItemView>() {

    lateinit var mOnCountrySelectedListener: OnCountrySelectedListener

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): CountryListItemView {
        return CountryListItemView_.build(parent.context)
    }

   override fun onBindViewHolder(holder: ViewWrapper<CountryListItemView>, position: Int) {
        holder.view.bind(getItem(position)).setOnCountrySelectedListener(mOnCountrySelectedListener)
    }

    fun setOnCountrySelectedListener(onCountrySelectedListener: OnCountrySelectedListener) {
        mOnCountrySelectedListener = onCountrySelectedListener
    }

    interface OnCountrySelectedListener {
        fun onCountrySelected(country: Country?)
    }
}
