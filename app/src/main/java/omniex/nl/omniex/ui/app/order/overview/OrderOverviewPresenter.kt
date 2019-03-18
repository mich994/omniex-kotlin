package omniex.nl.omniex.ui.app.order.overview

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.repository.OrderRepository
import omniex.nl.omniex.ui.base.BasePresenter
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class OrderOverviewPresenter @Inject
internal constructor(private val mOrderRepository: OrderRepository) : BasePresenter<OrderOverviewView>() {

    fun confirmOrder() {
        addDisposable(mOrderRepository
                .orderOverview()
                .flatMap { confirmAfterOverviewOrder(it) }
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { view -> view.onOrderConfirmed() }
                    else
                        ifViewAttached { view -> view.onOrderConfirmedFailed() }
                }) { error ->
                    ifViewAttached { view -> view.onOrderConfirmedFailed() }
                    Timber.e(error)
                })
    }

    fun confirmAfterOverviewOrder(postOrderResponse: Response<Void>): Single<Response<Void>> {
        return Single.defer<Response<Void>> {
            if (postOrderResponse.code() == 200)
                mOrderRepository.confirmOrder()
            else
                null
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
