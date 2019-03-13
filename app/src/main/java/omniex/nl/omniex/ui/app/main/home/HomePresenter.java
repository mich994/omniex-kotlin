package omniex.nl.omniex.ui.app.main.home;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.ProductsRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.ui.base.BaseView;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomeView> {

    private ProductsRepository mProductsRepository;

    @Inject
    HomePresenter(ProductsRepository productsRepository) {
        mProductsRepository = productsRepository;
    }

    void getListOfFeatured() {
        ifViewAttached(BaseView::startLoading);
        addDisposable(mProductsRepository
                .getListOfFeatured()
                .subscribe(featureProductsResponse -> {
                    if (featureProductsResponse.isSuccessful() && featureProductsResponse.code() == 200) {
                        ifViewAttached(
                                view -> view
                                        .onFeaturedProductsFetched(featureProductsResponse
                                                .body()
                                                .getProductWrappers()
                                                .get(0)
                                                .getFeaturedProducts()));
                    }
                    ifViewAttached(BaseView::stopLoading);
                }, error -> {
                    ifViewAttached(BaseView::stopLoading);
                    Timber.e(error);
                }));
    }

    void getBestseller() {
        addDisposable(mProductsRepository.getBestsellers()
                .subscribe(featuredProductsResponseResponse -> {
                }, Throwable::printStackTrace));
    }
}
