package omniex.nl.omniex.ui.app.auth.login

import android.app.AlertDialog
import android.view.View
import android.widget.EditText
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.auth.Login
import omniex.nl.omniex.ui.app.main.MainMenuActivity_
import omniex.nl.omniex.ui.base.BaseActivity
import omniex.nl.omniex.ui.views.toolbar.CustomToolbar
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById


@EActivity(R.layout.activity_login)
open class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {

    @ViewById(R.id.login_email_input)
    lateinit var mEmailInput: EditText

    @ViewById(R.id.login_password_input)
    lateinit var mPasswordInput: EditText

    @Click(R.id.login_login_btn, R.id.login_exit_btn)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.login_login_btn -> getPresenter().login(Login(mEmailInput!!.text.toString(), mPasswordInput!!.text.toString()))
            R.id.login_exit_btn -> finish()
        }
    }

    override fun onFirstCreate() {
        super.onFirstCreate()
        setToolbar()
    }

    private fun setToolbar() {
        customToolbar.setIconStart(R.drawable.twotone_arrow_back_black_36)
                .setIconStarClickListener(object : CustomToolbar.IconStartClickListener {
                    override fun onIconStartClick() {
                        this@LoginActivity.finish()
                    }
                })
    }

    override fun onLoginSuccess() {
        finish()
        MainMenuActivity_.intent(this).start()
    }

    override fun onLoginErrorMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
        builder.show()
    }
}
