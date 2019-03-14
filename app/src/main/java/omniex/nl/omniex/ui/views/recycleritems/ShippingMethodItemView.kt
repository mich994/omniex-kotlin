package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.shipping.ShippingMethod
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter

import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_shipping_method_item)
class ShippingMethodItemView(context: Context) : CardView(context) {

    @ViewById(R.id.shipping_method_cd)
    lateinit var mShippingMethodCd: CardView

    @ViewById(R.id.shipping_method_title)
    lateinit var mTitle: TextView

    @ViewById(R.id.shipping_method_subtitle)
    lateinit var mSubtitle: TextView

    @ViewById(R.id.shipping_method_price)
    lateinit var mPrice: TextView

    private var mItemClickListener: BaseRecyclerAdapter.ItemClickListener<ShippingMethod>? = null
    private var mShippingMethod: ShippingMethod? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(shippingMethod: ShippingMethod): ShippingMethodItemView {
        mShippingMethod = shippingMethod
        mTitle.text = mShippingMethod!!.weight!!.title
        mSubtitle.text = mShippingMethod!!.weight!!.quote!!.weightCode!!.title
        mPrice.text = mShippingMethod!!.weight!!.quote!!.weightCode!!.priceText
        mShippingMethodCd.setOnClickListener { v -> mItemClickListener!!.onItemClick(mShippingMethod) }
        return this
    }

    fun setItemClickListener(itemClickListener: BaseRecyclerAdapter.ItemClickListener<ShippingMethod>): ShippingMethodItemView {
        mItemClickListener = itemClickListener
        return this
    }

    override fun setSelected(isSelected: Boolean) {
        mShippingMethodCd!!.setCardBackgroundColor(if (isSelected) resources.getColor(R.color.colorPrimaryLight) else resources.getColor(R.color.icons))
    }
}
