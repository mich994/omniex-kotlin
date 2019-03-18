package omniex.nl.omniex.ui.app.auth

import android.support.v7.app.AppCompatActivity

import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity


import omniex.nl.omniex.R
import omniex.nl.omniex.ui.app.MainMenuActivity_
import omniex.nl.omniex.utils.SharedPrefUtils

@EActivity(R.layout.activity_start)
open class StartActivity : AppCompatActivity() {

    @Click(R.id.start_login_btn)
    internal fun onLoginClick() {
        LoginActivity_.intent(this).start()
    }

    @Click(R.id.start_register_btn)
    internal fun onRegisterClick() {
        RegisterActivity_.intent(this).start()
    }

    @Click(R.id.start_guest_btn)
    internal fun onStartGuestClick() {
        SharedPrefUtils.setUserGuest(true)
        MainMenuActivity_.intent(this).start()
    }
}
