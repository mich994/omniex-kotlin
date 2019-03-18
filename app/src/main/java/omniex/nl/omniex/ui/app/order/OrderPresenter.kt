package omniex.nl.omniex.ui.app.order


import omniex.nl.omniex.data.repository.AddressRepository
import omniex.nl.omniex.data.repository.CartRepository
import omniex.nl.omniex.data.repository.OrderRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class OrderPresenter @Inject
internal constructor(private val mOrderRepository: OrderRepository, private val mAddressRepository: AddressRepository, private val mCartRepository: CartRepository) : BasePresenter<OrderView>() {

    internal fun getCart() {
        addDisposable(mCartRepository
                .cart
                .subscribe({ cartResponse ->
                    if (cartResponse.code() === 200)
                        ifViewAttached { view -> view.onCartFetched(cartResponse.body()!!.cart!!) }
                }, { error(it.printStackTrace())}))
    }

    internal fun postOrderOverview() {
        addDisposable(mOrderRepository
                .orderOverview()
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached({view -> view.onOrderOverviewSuccess() })
                    else
                        ifViewAttached({view -> view.onOrderOverviewFailed() })
                }, { error ->
                    ifViewAttached({view -> view.onOrderOverviewFailed() })
                    Timber.e(error)
                }))
    }

}
