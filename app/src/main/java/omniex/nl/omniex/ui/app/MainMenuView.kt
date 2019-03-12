package omniex.nl.omniex.ui.app

import omniex.nl.omniex.ui.base.BaseView

interface MainMenuView : BaseView {
    fun onLogoutSuccess()
    fun onCartItemQuantityUpdated()
    fun onCartEmpty()
}
