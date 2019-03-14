package omniex.nl.omniex.ui.app.categories


import omniex.nl.omniex.data.model.products.Category
import omniex.nl.omniex.ui.base.BaseView

interface CategoriesView : BaseView {
    fun onCategoriesFetched(categoryList: List<Category>)
}
