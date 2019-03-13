package omniex.nl.omniex.ui.app.main.home;

import java.util.List;

import nl.omniex.omniexshopping.data.model.products.FeaturedProduct;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface HomeView extends BaseView {

    void onFeaturedProductsFetched(List<FeaturedProduct> featuredProducts);
}
