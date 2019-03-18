package omniex.nl.omniex.ui.app.auth.login

import omniex.nl.omniex.data.model.auth.Login
import omniex.nl.omniex.data.repository.ProfileRepository
import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.utils.SharedPrefUtils
import timber.log.Timber
import javax.inject.Inject

class LoginPresenter @Inject
internal constructor(private val mProfileRepository: ProfileRepository) : BasePresenter<LoginView>() {

    internal fun login(login: Login) {
        addDisposable(mProfileRepository
                .login(login)
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200) {
                        SharedPrefUtils.setUserGuest(false)
                        SharedPrefUtils.setUserLogged(true)
                        SharedPrefUtils.setTax(Integer.valueOf(voidResponse.body()!!.profile!!.accountCustomField!!.taxNumber))
                        SharedPrefUtils.setNewsletterStatus(voidResponse.body()!!.profile!!.newsletter.equals("1"))
                        ifViewAttached{it.onLoginSuccess()}
                    } else if (voidResponse.code() === 403) {
                        ifViewAttached { view -> view.onLoginErrorMessage("Email and/or password are not correct.") }
                    } else {
                        ifViewAttached { view -> view.onLoginErrorMessage("Something went wrong, please try again.") }
                    }
                }, { error ->
                    ifViewAttached { view -> view.onLoginErrorMessage("Please check your connection. If problem still occurs, please let us know by form on omniex.nl") }
                    error.printStackTrace()
                    Timber.e(error)
                }))
    }
}
