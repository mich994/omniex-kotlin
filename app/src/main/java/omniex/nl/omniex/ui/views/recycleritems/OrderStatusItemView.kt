package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.order.OrderStatus
import omniex.nl.omniex.utils.StringUtils

import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById



@EViewGroup(R.layout.view_order_status_item)
class OrderStatusItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.order_status_name)
    lateinit var mName: TextView
    @ViewById(R.id.order_status_date)
    lateinit var mDate: TextView
    @ViewById(R.id.order_status_price)
    lateinit var mPrice: TextView
    @ViewById(R.id.order_status_tv)
    lateinit var mOrderStatusTv: TextView

    private var mOrderStatus: OrderStatus? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(orderStatus: OrderStatus): OrderStatusItemView {
        mOrderStatus = orderStatus
        mName!!.setText(mOrderStatus!!.name)
        mDate!!.setText(StringUtils.fixUrl(mOrderStatus!!.dateAdded))
        mPrice!!.setText(mOrderStatus!!.products + " item/s - " + mOrderStatus!!.totalPrice)
        mOrderStatusTv!!.setText(mOrderStatus!!.status)
        return this
    }


}
