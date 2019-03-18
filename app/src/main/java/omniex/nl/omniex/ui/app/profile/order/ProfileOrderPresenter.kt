package omniex.nl.omniex.ui.app.profile.order

import omniex.nl.omniex.data.repository.OrderRepository
import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject

class ProfileOrderPresenter @Inject
internal constructor(private val mOrderRepository: OrderRepository) : BasePresenter<ProfileOrderView>() {

    fun getOrderStatuses() {
        addDisposable(mOrderRepository
                .orderStatuses()
                .subscribe(
                        { orderStatusesResponse ->
                            if (orderStatusesResponse.code() === 200)
                                ifViewAttached { view -> view.onOrderStatusesFetched(orderStatusesResponse.body()!!.orderStatusList!!) }
                        }, {it.printStackTrace()}))
    }

}
