package omniex.nl.omniex.ui.views.dialogs.address

import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Country
import omniex.nl.omniex.ui.adapters.CountriesAdapter
import org.androidannotations.annotations.*
import java.util.*
import javax.inject.Inject

@EFragment(R.layout.fragment_countries_dialog)
open class CountryDialog : DialogFragment(), CountriesAdapter.OnCountrySelectedListener {

    @ViewById(R.id.countries_dialog_rv)
    lateinit var mCountriesRv: RecyclerView

    @Inject
    lateinit var mCountriesAdapter: CountriesAdapter

    @FragmentArg
    lateinit var mCountries: ArrayList<Country>

    private var mOnCountrySelectedListener: CountriesAdapter.OnCountrySelectedListener? = null

    @AfterInject
    protected fun afterInject() {
        try {
            AndroidSupportInjection.inject(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    override fun onResume() {
        super.onResume()
        val params = dialog.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = params as android.view.WindowManager.LayoutParams
    }

    @AfterViews
    internal fun initCountryList() {
        mCountriesAdapter!!.setOnCountrySelectedListener(this)
        mCountriesRv!!.adapter = mCountriesAdapter
        mCountriesAdapter!!.setItems(mCountries!!)
    }

    fun setOnCountrySelectedListener(onCountrySelectedListener: CountriesAdapter.OnCountrySelectedListener): CountryDialog {
        mOnCountrySelectedListener = onCountrySelectedListener
        return this
    }

    override fun onCountrySelected(country: Country?) {
        mOnCountrySelectedListener!!.onCountrySelected(country)
        dismiss()
    }
}
