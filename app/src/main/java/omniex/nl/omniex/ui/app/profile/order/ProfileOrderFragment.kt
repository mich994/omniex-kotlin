package omniex.nl.omniex.ui.app.profile.order

import android.support.v7.widget.RecyclerView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.order.OrderStatus
import omniex.nl.omniex.ui.adapters.OrderStatutesAdapter
import omniex.nl.omniex.ui.base.BaseFragment
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_profile_order)
open class ProfileOrderFragment : BaseFragment<ProfileOrderView, ProfileOrderPresenter>(), ProfileOrderView {

    @ViewById(R.id.profile_order_list_rv)
    internal var mOrdersRv: RecyclerView? = null

    @Inject
    internal var mOrderStatutesAdapter: OrderStatutesAdapter? = null

    @AfterViews
    internal fun initOrdersList() {
        mOrdersRv!!.adapter = mOrderStatutesAdapter
        getPresenter().getOrderStatuses()
    }

    override fun onOrderStatusesFetched(orderStatusList: List<OrderStatus>) {
        mOrderStatutesAdapter!!.setItems(orderStatusList)
    }
}
