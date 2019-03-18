package omniex.nl.omniex.ui.app.auth.register

import android.app.AlertDialog
import android.view.View
import android.widget.EditText
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.auth.register.CustomField
import omniex.nl.omniex.data.model.auth.register.CustomFieldSetter
import omniex.nl.omniex.data.model.auth.register.RegisterSetter
import omniex.nl.omniex.ui.base.BaseActivity
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById

@EActivity(R.layout.activity_register)
open class RegisterActivity : BaseActivity<RegisterView, RegisterPresenter>(), RegisterView {

    @ViewById(R.id.register_input_email)
    lateinit var mEmail: EditText

    @ViewById(R.id.register_input_password)
    lateinit var mPassword: EditText

    @ViewById(R.id.register_input_confirm_password)
    lateinit var mConfirmPassword: EditText

    @ViewById(R.id.register_input_company_address)
    lateinit var mCompanyAddress: EditText

    @ViewById(R.id.register_input_company_name)
    lateinit var mCompanyName: EditText

    @ViewById(R.id.register_input_first_name)
    lateinit var mFirstName: EditText

    @ViewById(R.id.register_input_last_name)
    lateinit var mLastName: EditText

    @ViewById(R.id.register_input_telephone)
    lateinit var mTelephone: EditText

    @ViewById(R.id.register_input_country)
    lateinit var mCountry: EditText

    @ViewById(R.id.register_input_city)
    lateinit var mCity: EditText

    @ViewById(R.id.register_input_post_code)
    lateinit var mPostCode: EditText

    @ViewById(R.id.register_input_tax)
    lateinit var mTax: EditText


    private fun createRegisterObject(): RegisterSetter {
        val registerSetter = RegisterSetter(
                mFirstName!!.text.toString(),
                mLastName!!.text.toString(),
                mEmail!!.text.toString(),
                mPassword!!.text.toString(),
                mConfirmPassword!!.text.toString(),
                mTelephone!!.text.toString(),
                1,
                CustomField(CustomFieldSetter(
                        mCompanyAddress!!.text.toString(),
                        mCompanyName!!.text.toString(),
                        mTax!!.text.toString(),
                        mCity!!.text.toString(),
                        mCountry!!.text.toString())))
        return registerSetter
    }

    @Click(R.id.register_btn_register, R.id.register_btn_cancel)
    internal fun onClick(v: View) {
        if (v.id == R.id.register_btn_register) {
            getPresenter().register(createRegisterObject())
        } else {
            finish()
        }
    }

    private fun showWarningDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
                .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                .show()
    }

    override fun onRegisterSuccess() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Register succeed")
                .setCancelable(false)
                .setMessage("You will receive a confirmation email. Please confirm your registration. After that you will be able to login when we will accept your account.")
                .setPositiveButton("Ok") { dialog, which ->
                    finish()
                    dialog.dismiss()
                }
                .show()
    }

    override fun onRegisterFailed(message: String) {
        showWarningDialog(message)
    }

    override fun onConnectionFailed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please check your connection. If problem still occurs, please let us know by form on omniex.nl")
                .setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                .show()
    }

    override fun onFirstCreate() {
        super.onFirstCreate()
        setToolbar()

    }

    private fun setToolbar() {
//        getCustomToolbar()
//                .setIconStart(R.drawable.twotone_arrow_back_black_36)
//                .setIconStarClickListener(???({ this.finish() }))
    }
}
