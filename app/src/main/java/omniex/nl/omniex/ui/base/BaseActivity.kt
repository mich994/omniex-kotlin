package omniex.nl.omniex.ui.base

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import dagger.Lazy
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EActivity
import javax.inject.Inject

@EActivity
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : MvpActivity<V, P>(), BaseView {

//    @ViewById(R.id.progress_bar)
//    var mProgressBar: FrameLayout? = null
//
//    @Bean
//    var customToolbar: CustomToolbar? = null
//        protected set

    private val mCompositeDisposable = CompositeDisposable()
    private var mFragmentManager: FragmentManager? = null
    private var mOnBackPressListener: OnBackPressListener? = null

    @Inject
    lateinit var mPresenter: Lazy<P>

    @AfterInject
    protected fun afterInject() {
        try {
            AndroidInjection.inject(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentManager = supportFragmentManager

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null)
            onFirstCreate()
    }

    open fun onFirstCreate() {}

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

//    fun goToFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
//        val fragmentContainer = mFragmentManager!!.findFragmentById(R.id.fragment_container)
//        if (fragmentContainer != null && fragmentContainer.javaClass.simpleName == fragment.javaClass.simpleName)
//            return
//        val transaction = mFragmentManager!!.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment)
//        if (addToBackStack)
//            transaction.addToBackStack(tag)
//        transaction.commit()
//    }

    fun popBackStack() {
        mFragmentManager!!.popBackStack()
    }

    fun setOnBackPressListener(onBackPressListener: OnBackPressListener) {
        mOnBackPressListener = onBackPressListener
    }

    override fun onBackPressed() {
        if (mFragmentManager!!.backStackEntryCount > 1)
            popBackStack()
        else
            super.onBackPressed()

    }

//    fun showProgressBar() {
//        mProgressBar!!.visibility = View.VISIBLE
//    }
//
//    fun hideProgressBar() {
//        mProgressBar!!.visibility = View.GONE
//    }

    @Throws(IllegalArgumentException::class)
    fun showToastMessage(message: String, duration: Int) {
        when (duration) {
            0 -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            else -> throw IllegalArgumentException()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }

    override fun createPresenter(): P {
        return mPresenter.get()
    }

    interface OnBackPressListener {
        fun onBackPressed()
    }
}