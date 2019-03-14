package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.adapters.ZonesAdapter
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_zone_list_item)
open class ZoneListItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.zone_item_name)
    lateinit var mZoneName: TextView

    private var mOnZoneSelectedClickListener: ZonesAdapter.OnZoneSelectedClickListener? = null
    private var mProvince: Province? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(province: Province?): ZoneListItemView {
        mProvince = province
        mZoneName!!.setText(province!!.fullName)
        return this
    }

    fun setOnZoneSelectedClickListener(onZoneSelectedClickListener: ZonesAdapter.OnZoneSelectedClickListener) {
        mOnZoneSelectedClickListener = onZoneSelectedClickListener
    }

    @Click(R.id.zone_item_layout)
    internal fun onCountryClick() {
        mOnZoneSelectedClickListener!!.onZoneSelected(mProvince)
    }
}