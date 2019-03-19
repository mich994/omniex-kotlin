package omniex.nl.omniex.ui.views.dialogs.address

import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.adapters.ZonesAdapter
import org.androidannotations.annotations.*
import java.util.*
import javax.inject.Inject


@EFragment(R.layout.fragment_zones_dialog)
open class ZoneDialog : DialogFragment(), ZonesAdapter.OnZoneSelectedClickListener {

    @ViewById(R.id.zones_dialog_rv)
    lateinit var mZonesRv: RecyclerView

    @Inject
    lateinit var mZonesAdapter: ZonesAdapter

    @FragmentArg
    lateinit var mProvinces: ArrayList<Province>

    private var mOnZoneSelectedClickListener: ZonesAdapter.OnZoneSelectedClickListener? = null

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
    internal fun initZoneList() {
        mZonesAdapter!!.setOnZoneSelectedClickListener(this)
        mZonesRv!!.adapter = mZonesAdapter
        mZonesAdapter!!.setItems(mProvinces!!)
    }

    fun setOnZoneSelectedClickListener(onZoneSelectedClickListener: ZonesAdapter.OnZoneSelectedClickListener): ZoneDialog {
        mOnZoneSelectedClickListener = onZoneSelectedClickListener
        return this
    }

    override fun onZoneSelected(province: Province?) {
        mOnZoneSelectedClickListener!!.onZoneSelected(province)
        dismiss()
    }
}