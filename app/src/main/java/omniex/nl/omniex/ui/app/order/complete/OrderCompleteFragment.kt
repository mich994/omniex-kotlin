package omniex.nl.omniex.ui.app.order.complete

import omniex.nl.omniex.R
import omniex.nl.omniex.ui.base.BaseFragment
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment


@EFragment(R.layout.fragment_order_complete)
open class OrderCompleteFragment : BaseFragment<OrderCompleteView, OrderCompletePresenter>() {

    @Click(R.id.order_complete_btn)
    fun onBackBtnClick() {
        getActivity()!!.finish()
    }
}
