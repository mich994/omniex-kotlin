package omniex.nl.omniex.ui.app.main.home

import nl.omniex.omniexshopping.data.model.products.FeaturedProduct
import nl.omniex.omniexshopping.ui.base.BaseView

interface HomeView : BaseView {

    fun onFeaturedProductsFetched(featuredProducts: List<FeaturedProduct>)
}
