package omniex.nl.omniex.ui.app.product;

import java.util.Objects;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.cart.AddToCartModel;
import nl.omniex.omniexshopping.data.repository.CartRepository;
import nl.omniex.omniexshopping.data.repository.ProductsRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.ui.base.BaseView;
import timber.log.Timber;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    private ProductsRepository mProductsRepository;
    private CartRepository mCartRepository;

    @Inject
    ProductDetailsPresenter(ProductsRepository productsRepository, CartRepository cartRepository) {
        mProductsRepository = productsRepository;
        mCartRepository = cartRepository;
    }

    void getProductDetails(Integer id) {
        ifViewAttached(
                BaseView::startLoading);
        addDisposable(mProductsRepository
                .getProductDetails(id)
                .subscribe(
                        productResponse -> {
                            if (productResponse.isSuccessful() && productResponse.code() == 200) {
                                ifViewAttached(view -> view.onDetailsFetched(Objects.requireNonNull(productResponse.body()).getProduct()));
                            }
                            ifViewAttached(BaseView::stopLoading);
                        },
                        error -> {
                            ifViewAttached(BaseView::stopLoading);
                            error.printStackTrace();
                            Timber.e(error);
                        }));
    }

    void addToCart(AddToCartModel addToCartModel) {
        ifViewAttached(ProductDetailsView::startLoading);
        addDisposable(mCartRepository
                .addToCart(addToCartModel)
                .subscribe(
                        voidResponse -> {
                            if(voidResponse.code()==200){}
                            ifViewAttached(ProductDetailsView::stopLoading);
                        },
                        error -> {
                            ifViewAttached(ProductDetailsView::stopLoading);
                            Timber.e(error);
                        }));
    }
}
