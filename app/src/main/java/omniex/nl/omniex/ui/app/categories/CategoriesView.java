package omniex.nl.omniex.ui.app.categories;

import java.util.List;

import nl.omniex.omniexshopping.data.model.products.Category;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface CategoriesView extends BaseView {
    void onCategoriesFetched(List<Category> categoryList);
}
