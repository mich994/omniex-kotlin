package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup
import omniex.nl.omniex.data.model.address.Province
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.ZoneListItemView

import org.androidannotations.annotations.EBean


@EBean
class ZonesAdapter : BaseRecyclerAdapter<Province, ZoneListItemView>() {

    lateinit var mOnZoneSelectedClickListener: OnZoneSelectedClickListener

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ZoneListItemView {
        return ZoneListItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<ZoneListItemView>, position: Int) {
        holder.view.bind(getItem(position)).setOnZoneSelectedClickListener(mOnZoneSelectedClickListener)
    }

    fun setOnZoneSelectedClickListener(onZoneSelectedClickListener: OnZoneSelectedClickListener) {
        mOnZoneSelectedClickListener = onZoneSelectedClickListener
    }

    interface OnZoneSelectedClickListener {
        fun onZoneSelected(province: Province?)
    }
}
