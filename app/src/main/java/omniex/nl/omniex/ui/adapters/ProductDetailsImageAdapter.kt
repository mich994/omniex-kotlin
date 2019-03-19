package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.ProductDetailsImageItemView
import omniex.nl.omniex.ui.views.recycleritems.ProductDetailsImageItemView_
import org.androidannotations.annotations.EBean

@EBean
open class ProductDetailsImageAdapter : BaseRecyclerAdapter<String, ProductDetailsImageItemView>() {

    lateinit var mDetailsImageClickListener: OnDetailsImageClickListener

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProductDetailsImageItemView {
        return ProductDetailsImageItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<ProductDetailsImageItemView>, position: Int) {
        holder.view
                .bind(getItem(position), position)
                .setDetailsImageClickListener(mDetailsImageClickListener)
    }

    fun setDetailsImageClickListener(detailsImageClickListener: OnDetailsImageClickListener) {
        mDetailsImageClickListener = detailsImageClickListener
    }

    interface OnDetailsImageClickListener {
        fun onImageClick(position: Int)
    }
}
