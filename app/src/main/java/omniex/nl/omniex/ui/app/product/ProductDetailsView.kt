package omniex.nl.omniex.ui.app.product

import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.base.BaseView


interface ProductDetailsView : BaseView {
    fun onDetailsFetched(product: Product)
}
