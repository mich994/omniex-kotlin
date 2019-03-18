package omniex.nl.omniex.ui.app.auth.login

import omniex.nl.omniex.ui.base.BaseView

interface LoginView : BaseView {
    fun onLoginSuccess()
    fun onLoginErrorMessage(message: String)
}
