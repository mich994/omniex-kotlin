package omniex.nl.omniex.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import omniex.nl.omniex.R
import omniex.nl.omniex.ui.base.menu.BaseMenuActivity
import omniex.nl.omniex.ui.views.toolbar.CustomToolbar
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EFragment
import javax.inject.Inject

@EFragment
abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : MvpFragment<V, P>(), BaseView {

    private val mCompositeDisposable = CompositeDisposable()
    protected var baseActivity: BaseActivity<*,*>? = null
    lateinit var mBaseMenuActivity: BaseMenuActivity<*,*,*>
    private var mEnableProgressBar: Boolean = false

    @Inject
    lateinit var mPresenter: Lazy<P>

    @AfterInject
    protected fun afterInject() {
        try {
            AndroidSupportInjection.inject(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    override fun createPresenter(): P {
        return mPresenter!!.get()
    }

    override fun getPresenter(): P {
        return super.getPresenter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*,*>)
            baseActivity = activity as BaseActivity<*,*>?
    }

     override fun onResume() {
        super.onResume()
        if (baseActivity != null && baseActivity!!.customToolbar != null) {
            setToolbarIconMenuActivity()
        }
    }

    protected fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        baseActivity!!.goToFragment(fragment, addToBackStack, "")
    }

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }

    protected fun setToolbarIconMenuActivity() {
        try {
            mBaseMenuActivity = activity as BaseMenuActivity<*,*,*>
            if (mBaseMenuActivity!!.getSupportFragmentManager().getBackStackEntryCount() > 1)
                baseActivity!!.customToolbar
                        .setIconStart(R.drawable.twotone_arrow_back_black_36)
                        .setIconStarClickListener( object: CustomToolbar.IconStartClickListener{
                            override fun onIconStartClick() {
                                mBaseMenuActivity.getSupportFragmentManager().popBackStack()
                            }
                        })
            else
                baseActivity!!.customToolbar
                        .setIconStart(R.drawable.twotone_menu_black_36)
                        .setIconStarClickListener(object : CustomToolbar.IconStartClickListener{
                            override fun onIconStartClick() {
                                mBaseMenuActivity.toggleMenu()                            }
                        })
        } catch (e: ClassCastException) {

        }

    }

    protected fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEnableProgressBar = true
    }

    override fun startLoading() {
        if (mEnableProgressBar)
            baseActivity!!.showProgressBar()
    }

    override fun stopLoading() {
        baseActivity!!.hideProgressBar()
        mEnableProgressBar = false
    }
}
