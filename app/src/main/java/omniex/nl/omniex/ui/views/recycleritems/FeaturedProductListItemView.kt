package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.FeaturedProduct
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.utils.StringUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_featured_product_list_item)
class FeaturedProductListItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.featured_product_list_item_image)
    lateinit var mProductImage: ImageView

    @ViewById(R.id.featured_product_list_item_name)
    lateinit var mProductName: TextView

    @ViewById(R.id.featured_product_list_item_price)
    lateinit var mProductPrice: TextView

    private var mFeaturedProduct: FeaturedProduct? = null
    private var mProductItemClickListener: BaseRecyclerAdapter.ItemClickListener<FeaturedProduct>? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(featuredProduct: FeaturedProduct): FeaturedProductListItemView {
        mFeaturedProduct = featuredProduct
        Glide.with(this)
                .load(StringUtils.fixUrl(featuredProduct.thumb))
                .into(mProductImage!!)
        mProductName!!.setText(mFeaturedProduct!!.name)
        mProductPrice!!.setText(mFeaturedProduct!!.priceExcludingTaxFormatted)
        return this
    }

    fun setProductItemClickListener(productItemClickListener: BaseRecyclerAdapter.ItemClickListener<FeaturedProduct>) {
        mProductItemClickListener = productItemClickListener
    }

    @Click(R.id.product_list_item_fl)
    internal fun onItemClick() {
        mProductItemClickListener!!.onItemClick(mFeaturedProduct)
    }
}
