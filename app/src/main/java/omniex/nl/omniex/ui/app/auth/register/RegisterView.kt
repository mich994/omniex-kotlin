package omniex.nl.omniex.ui.app.auth.register

import omniex.nl.omniex.ui.base.BaseView

interface RegisterView : BaseView {

    fun onRegisterSuccess()
    fun onRegisterFailed(message: String)
    fun onConnectionFailed()
}
