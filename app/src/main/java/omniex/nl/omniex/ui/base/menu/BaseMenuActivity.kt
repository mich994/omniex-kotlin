package omniex.nl.omniex.ui.base.menu


import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout.LOCK_MODE_LOCKED_CLOSED
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout.LOCK_MODE_UNLOCKED
import omniex.nl.omniex.ui.base.BaseActivity
import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.ui.base.BaseView
import org.androidannotations.annotations.EBean

@EBean
abstract class BaseMenuActivity<MenuAdapter : BaseMenuAdapter<*, *>, MView : BaseView, MPresenter : BasePresenter<MView>> : BaseActivity<MView, MPresenter>(), MenuHelper.MenuBase<MenuAdapter> {

     var mMenuHelper: MenuHelper<MenuAdapter>?=null
     var mDuoDrawerLayout: DuoDrawerLayout?=null
     var mOverlay: View?=null

    @get:IdRes
    abstract val overlayRes: Int

    val baseMenuActivity: BaseMenuActivity<*, *, *>
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMenuHelper = MenuHelper(this)
    }

    override fun onFirstCreate() {
        super.onFirstCreate()
        mOverlay = findViewById(overlayRes)
        supportFragmentManager.addOnBackStackChangedListener {
            FragmentManager.OnBackStackChangedListener {
                if (supportFragmentManager.getBackStackEntryCount() > 1) {
                mDuoDrawerLayout!!.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
            } else {
                mDuoDrawerLayout!!.setDrawerLockMode(LOCK_MODE_UNLOCKED)
            }
            }
        }

        mMenuHelper!!.onSetContent()
        mDuoDrawerLayout = mMenuHelper!!.mMenuViewHolder!!.mDuoDrawerLayout
        mDuoDrawerLayout!!.setDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

        })
    }

    override fun onOptionClick(position: Int, option: Any) {}

    override fun closeMenu() {
        mDuoDrawerLayout!!.closeDrawer()
    }

    override fun openMenu() {
        mDuoDrawerLayout!!.openDrawer()
    }

    override fun toggleMenu() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            if (mDuoDrawerLayout!!.isDrawerOpen)
                closeMenu()
            else
                openMenu()
        }
    }

    override fun activity(): AppCompatActivity = this
}
