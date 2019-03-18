package omniex.nl.omniex.ui.app.main.home

import omniex.nl.omniex.data.repository.ProductsRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class HomePresenter @Inject
internal constructor(private val mProductsRepository: ProductsRepository) : BasePresenter<HomeView>() {

    internal fun getListOfFeatured() {
        ifViewAttached { it.startLoading() }
        addDisposable(mProductsRepository
                .listOfFeatured()
                .subscribe({ featureProductsResponse ->
                    if (featureProductsResponse.isSuccessful() && featureProductsResponse.code() === 200) {
                        ifViewAttached { view ->
                            view
                                    .onFeaturedProductsFetched(featureProductsResponse
                                            .body()!!
                                            .productWrappers!!
                                            .get(0)
                                            .featuredProducts!!)
                        }
                    }
                    ifViewAttached { it.stopLoading() }
                }, { error ->
                    ifViewAttached { it.stopLoading() }
                    Timber.e(error)
                }))
    }

    internal fun getBestseller() {
        addDisposable(mProductsRepository.bestsellers()
                .subscribe({ featuredProductsResponseResponse -> }, { error -> error.printStackTrace() }))
    }
}
