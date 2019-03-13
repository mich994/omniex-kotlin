package omniex.nl.omniex.ui.app.product.list;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.ProductsRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.ui.base.BaseView;
import timber.log.Timber;

public class ProductsListPresenter extends BasePresenter<ProductsListView> {

    private ProductsRepository mProductsRepository;

    @Inject
    ProductsListPresenter(ProductsRepository productsRepository) {
        mProductsRepository = productsRepository;
    }

    void getProductsList(Integer catId) {
        ifViewAttached(BaseView::startLoading);
        addDisposable(mProductsRepository
                .getProductsByCat(catId)
                .subscribe(
                        productsListResponse -> {
                            if(productsListResponse.isSuccessful() && productsListResponse.code()==200){
                                ifViewAttached(view -> view.onProductsListFetched(productsListResponse.body().getProductList()));
                            }
                            ifViewAttached(BaseView::stopLoading);

                        }, error->{
                            Timber.e(error);
                            ifViewAttached(BaseView::stopLoading);
                        }));
    }

    void getBestsellers() {
        ifViewAttached(BaseView::startLoading);
        addDisposable(mProductsRepository
                .getBestsellers()
                .subscribe(
                        productsListResponse -> {
                            if(productsListResponse.isSuccessful() && productsListResponse.code()==200){
                                ifViewAttached(view -> view.onProductsListFetched(productsListResponse.body().getProductList()));
                            }
                            ifViewAttached(BaseView::stopLoading);

                        }, error->{
                            Timber.e(error);
                            ifViewAttached(BaseView::stopLoading);
                        }));
    }
}
