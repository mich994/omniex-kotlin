package omniex.nl.omniex.ui.app.product.list;

import java.util.List;

import nl.omniex.omniexshopping.data.model.products.Product;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface ProductsListView extends BaseView {
    void onProductsListFetched(List<Product> productList);
}
