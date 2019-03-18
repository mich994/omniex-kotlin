package omniex.nl.omniex.ui.app.product

import omniex.nl.omniex.data.model.cart.AddToCartModel
import omniex.nl.omniex.data.repository.CartRepository
import omniex.nl.omniex.data.repository.ProductsRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class ProductDetailsPresenter @Inject
internal constructor(private val mProductsRepository: ProductsRepository, private val mCartRepository: CartRepository) : BasePresenter<ProductDetailsView>() {

    internal fun getProductDetails(id: Int?) {
        ifViewAttached({it.startLoading()})
        addDisposable(mProductsRepository
                .getProductDetails(id)
                .subscribe(
                        { productResponse ->
                            if (productResponse.isSuccessful() && productResponse.code() === 200) {
                                ifViewAttached { view -> view.onDetailsFetched(Objects.requireNonNull(productResponse.body())!!.product!!) }
                            }
                            ifViewAttached({it.stopLoading()})
                        },
                        { error ->
                            ifViewAttached({it.stopLoading()})
                            error.printStackTrace()
                            Timber.e(error)
                        }))
    }

    internal fun addToCart(addToCartModel: AddToCartModel) {
        ifViewAttached({it.startLoading()})
        addDisposable(mCartRepository
                .addToCart(addToCartModel)
                .subscribe(
                        { voidResponse ->
                            if (voidResponse.code() === 200) {
                            }
                            ifViewAttached({it.stopLoading()})
                        },
                        { error ->
                            ifViewAttached({it.stopLoading()})
                            Timber.e(error)
                        }))
    }
}
