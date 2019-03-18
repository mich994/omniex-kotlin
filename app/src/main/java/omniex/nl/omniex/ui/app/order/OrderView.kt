package omniex.nl.omniex.ui.app.order

import omniex.nl.omniex.ui.base.BaseView


interface OrderView : BaseView {
    fun onOrderOverviewSuccess()
    fun onOrderOverviewFailed()
}
