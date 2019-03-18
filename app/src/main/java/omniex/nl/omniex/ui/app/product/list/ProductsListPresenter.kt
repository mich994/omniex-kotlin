package omniex.nl.omniex.ui.app.product.list

import omniex.nl.omniex.data.repository.ProductsRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class ProductsListPresenter @Inject
internal constructor(private val mProductsRepository: ProductsRepository) : BasePresenter<ProductsListView>() {

    internal fun getProductsList(catId: Int?) {
        ifViewAttached{it.startLoading()}
        addDisposable(mProductsRepository
                .getProductsByCat(catId)
                .subscribe(
                        { productsListResponse ->
                            if (productsListResponse.isSuccessful() && productsListResponse.code() === 200) {
                                ifViewAttached { view -> view.onProductsListFetched(productsListResponse.body()!!.productList!!) }
                            }
                            ifViewAttached{it.stopLoading()}

                        }, { error ->
                    Timber.e(error)
                    ifViewAttached{it.stopLoading()}
                }))
    }

    internal fun getBestsellers() {
        ifViewAttached{it.startLoading()}
        addDisposable(mProductsRepository
                .bestsellers()
                .subscribe(
                        { productsListResponse ->
                            if (productsListResponse.isSuccessful() && productsListResponse.code() === 200) {
                                ifViewAttached { view -> view.onProductsListFetched(productsListResponse.body()!!.productList!!) }
                            }
                            ifViewAttached{it.stopLoading()}

                        }, { error ->
                    Timber.e(error)
                    ifViewAttached{it.stopLoading()}
                }))
    }
}
