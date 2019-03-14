package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.utils.StringUtils

import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById


@EViewGroup(R.layout.view_product_list_item)
class ProductsListItemView(context: Context) : FrameLayout(context) {

    @ViewById(R.id.product_list_item_image)
    lateinit var mProductImage: ImageView

    @ViewById(R.id.product_list_item_name)
    lateinit var mProductName: TextView

    @ViewById(R.id.product_list_item_price)
    lateinit var mProductPrice: TextView

    private var mProduct: Product? = null
    private var mProductItemClickListener: BaseRecyclerAdapter.ItemClickListener<Product>? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(product: Product): ProductsListItemView {
        mProduct = product
        Glide.with(this)
                .load(if (mProduct!!.imageUrl != null) StringUtils.fixUrl(mProduct!!.imageUrl) else mProduct!!.imageThumb)
                .into(mProductImage!!)
                .onLoadFailed(resources.getDrawable(R.drawable.plchdr))
        mProductName!!.setText(mProduct!!.name)
        mProductPrice!!.setText(mProduct!!.priceExcTaxFormated)
        return this
    }

    fun setProductItemClickListener(productItemClickListener: BaseRecyclerAdapter.ItemClickListener<Product>) {
        mProductItemClickListener = productItemClickListener
    }

    @Click(R.id.product_list_item_fl)
    internal fun onItemClick() {
        mProductItemClickListener!!.onItemClick(mProduct)
    }

}
