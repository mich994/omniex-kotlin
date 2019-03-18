package omniex.nl.omniex.ui.app.main.home

import omniex.nl.omniex.data.model.products.FeaturedProduct
import omniex.nl.omniex.ui.base.BaseView

interface HomeView : BaseView {

    fun onFeaturedProductsFetched(featuredProducts: List<FeaturedProduct>)
}
