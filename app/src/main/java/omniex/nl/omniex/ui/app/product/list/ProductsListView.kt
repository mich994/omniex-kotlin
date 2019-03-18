package omniex.nl.omniex.ui.app.product.list

import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.base.BaseView

interface ProductsListView : BaseView {
    fun onProductsListFetched(productList: List<Product>)
}
