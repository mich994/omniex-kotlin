package omniex.nl.omniex.ui.app.order.overview

import omniex.nl.omniex.ui.base.BaseView


interface OrderOverviewView : BaseView {
    fun onOrderConfirmed()
    fun onOrderConfirmedFailed()
}
