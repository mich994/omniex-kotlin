package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import nl.omniex.omniexshopping.data.model.products.FeaturedProduct
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter
import nl.omniex.omniexshopping.ui.views.recycleritems.FeaturedProductListItemView
import nl.omniex.omniexshopping.ui.views.recycleritems.FeaturedProductListItemView_
import omniex.nl.omniex.data.model.products.FeaturedProduct
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.FeaturedProductListItemView

@EBean
class FeatureProductsAdapter : BaseRecyclerAdapter<FeaturedProduct, FeaturedProductListItemView>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): FeaturedProductListItemView {
        return FeaturedProductListItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<FeaturedProductListItemView>, position: Int) {
        holder.view
                .bind(getItem(position))
                .setProductItemClickListener(itemClickListener)
    }
}
