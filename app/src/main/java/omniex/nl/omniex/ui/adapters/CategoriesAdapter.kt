package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup
import omniex.nl.omniex.data.model.products.Category
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.CategoryItemView
import omniex.nl.omniex.ui.views.recycleritems.CategoryItemView_
import org.androidannotations.annotations.EBean

@EBean
open class CategoriesAdapter : BaseRecyclerAdapter<Category, CategoryItemView>() {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): CategoryItemView {
        return CategoryItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<CategoryItemView>, position: Int) {
        holder.view
                .bind(getItem(position))
                .setItemClickListener(itemClickListener)
    }
}
