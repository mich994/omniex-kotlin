package omniex.nl.omniex.ui.base.menu

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import omniex.nl.omniex.R

class MenuHelper<MenuAdapter : BaseMenuAdapter<*, *>>(private val mMenuBase: MenuBase<MenuAdapter>) : DuoMenuView.OnMenuClickListener {

    lateinit var mMenuViewHolder: MenuViewHolder
    private var mMenuAdapter: MenuAdapter? = null

    fun onSetContent() {
        mMenuViewHolder = MenuViewHolder(mMenuBase.activity())
        mMenuBase.activity().setSupportActionBar(mMenuViewHolder.mToolbar)
        initializeMenu()
    }

    private fun initializeMenu() {
        mMenuAdapter = mMenuBase.createAdapter()
        mMenuViewHolder.mDuoMenuView.setOnMenuClickListener(this)
        mMenuViewHolder.mDuoMenuView.adapter = mMenuAdapter
        initializeDrawer()

        val defaultIndex = mMenuBase.startIndex()
        if (defaultIndex != -1) {
            onOptionClicked(mMenuBase.startIndex(), mMenuAdapter!!.getName(mMenuBase.activity(), mMenuAdapter!!.getItem(defaultIndex)))
        }
    }

    private fun initializeDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(mMenuBase.activity(), mMenuViewHolder.mDuoDrawerLayout, mMenuViewHolder.mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mMenuViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()
    }

    override fun onFooterClicked() {

    }

    override fun onHeaderClicked() {

    }

    override fun onOptionClicked(position: Int, objectClicked: Any) {
        mMenuBase.activity().title = mMenuAdapter!!.getName(mMenuBase.activity(), mMenuAdapter!!.getItem(position))
        mMenuAdapter!!.setSelected(position)
        mMenuBase.onOptionClicked(position, objectClicked)
        mMenuViewHolder!!.mDuoDrawerLayout.closeDrawer()
    }

    inner class MenuViewHolder internal constructor(activity: Activity) {
        internal var mDuoMenuView: DuoMenuView
        internal var mToolbar: Toolbar?=null
        var mDuoDrawerLayout: DuoDrawerLayout

        init {
            mDuoDrawerLayout = activity.findViewById(mMenuBase.duoDrawerLayout())
            mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
            try {
                mToolbar = activity!!.findViewById(mMenuBase.toolbarId())
            }catch (e:IllegalStateException){

            }

        }
    }

    interface MenuBase<MenuAdapter : BaseMenuAdapter<*, *>> : DuoMenuView.OnMenuClickListener {

        fun toolbarId ():Int
        fun duoDrawerLayout(): Int
        fun startIndex(): Int
        fun activity(): AppCompatActivity

        fun onOptionClick(position: Int, option: Any)
        fun closeMenu()
        fun openMenu()
        fun toggleMenu()
        fun createAdapter(): MenuAdapter


        override fun onFooterClicked() {

        }

        override fun onHeaderClicked() {

        }

        override fun onOptionClicked(position: Int, objectClicked: Any) {

        }
    }

}
