package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup

import org.androidannotations.annotations.EBean

import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.ProductsListItemView

@EBean
class ProductsListAdapter : BaseRecyclerAdapter<Product, ProductsListItemView>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): ProductsListItemView {
        return ProductsListItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<ProductsListItemView>, position: Int) {
        holder.view.bind(getItem(position)).setProductItemClickListener(getItemClickListener())
    }
}
