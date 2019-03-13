package omniex.nl.omniex.ui.app.categories;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.ProductsRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.ui.base.BaseView;
import timber.log.Timber;

public class CategoriesPresenter extends BasePresenter<CategoriesView> {

    private ProductsRepository mProductsRepository;

    @Inject
    CategoriesPresenter(ProductsRepository productsRepository) {
        mProductsRepository = productsRepository;
    }

    void getCategories() {
        ifViewAttached(BaseView::startLoading);
        addDisposable(mProductsRepository
                .getCategories()
                .subscribe(
                        categoriesResponse -> {
                            if(categoriesResponse.isSuccessful() && categoriesResponse.code()==200){
                                ifViewAttached(view -> view.onCategoriesFetched(categoriesResponse.body().getCategoryList()));
                            }
                            ifViewAttached(BaseView::stopLoading);
                        }, error->{
                            ifViewAttached(BaseView::stopLoading);
                            Timber.e(error);
                        }));
    }

}
