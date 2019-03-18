package omniex.nl.omniex.utils

import android.content.Context
import android.content.SharedPreferences
import omniex.nl.omniex.di.Application
import javax.inject.Inject


class SharedPrefUtils @Inject
internal constructor(private val mContext: Context) {
    fun accessToken(): String = sharedPref
            .getString(ACCESS_TOKEN_KEY, "")

    fun oldToken(): String = sharedPref
            .getString(OLD_TOKEN_KEY, "")

    fun saveAccessToken(token: String) {
        sharedPref
                .edit()
                .putString(ACCESS_TOKEN_KEY, token)
                .apply()
    }

    fun saveOldToken(oldToken: String) {
        sharedPref
                .edit()
                .putString(OLD_TOKEN_KEY, oldToken)
                .apply()
    }

    companion object {

        private val ACCESS_TOKEN_KEY = "ACCESS_TOKEN"
        private val OLD_TOKEN_KEY = "OLD_TOKEN"
        private val IS_USER_LOGGED_KEY = "IS_USER_LOGGED"
        private val IS_USER_GUEST = "IS_USER_GUEST"
        private val NEWSLETTER_STATUS_KEY = "IS_SUBSCRIBED"
        private val TAX_KEY = "TAX"

        private val sharedPref: SharedPreferences
            get() = Application.instance.getSharedPreferences("nl.omniex.omniexshopping", Context.MODE_PRIVATE)

        fun isUserLogged(): Boolean = sharedPref
                .getBoolean(IS_USER_LOGGED_KEY, false)

        fun setUserLogged(logged: Boolean) = sharedPref
                .edit()
                .putBoolean(IS_USER_LOGGED_KEY, logged)
                .apply()

        fun setUserGuest(isGuest: Boolean) {
            sharedPref
                    .edit()
                    .putBoolean(IS_USER_GUEST, isGuest)
                    .apply()
        }

        fun isUserQuest(): Boolean = sharedPref
                .getBoolean(IS_USER_GUEST, false)

        fun setNewsletterStatus(isSubscribed: Boolean) {
            sharedPref
                    .edit()
                    .putBoolean(NEWSLETTER_STATUS_KEY, isSubscribed)
                    .apply()
        }

        fun isNewsletterSubscribed(): Boolean = sharedPref
                .getBoolean(NEWSLETTER_STATUS_KEY, false)

        fun taxValue(): Int? = sharedPref
                .getInt(TAX_KEY, 1)

        fun setTax(tax: Int) = sharedPref
                .edit()
                .putInt(TAX_KEY, tax)
                .apply()
    }
}
