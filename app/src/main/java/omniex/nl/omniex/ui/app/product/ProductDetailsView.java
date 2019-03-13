package omniex.nl.omniex.ui.app.product;

import nl.omniex.omniexshopping.data.model.products.Product;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface ProductDetailsView extends BaseView {
    void onDetailsFetched(Product product);
}
