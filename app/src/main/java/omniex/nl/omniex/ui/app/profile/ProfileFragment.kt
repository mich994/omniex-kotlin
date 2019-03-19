package omniex.nl.omniex.ui.app.profile

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import omniex.nl.omniex.R
import omniex.nl.omniex.ui.adapters.ProfileViewPagerAdapter
import omniex.nl.omniex.ui.base.BaseFragment

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById


@EFragment(R.layout.fragment_profile)
open class ProfileFragment : BaseFragment<ProfileView, ProfilePresenter>(), ProfileView {

    @ViewById(R.id.profile_vp)
    lateinit var mProfileVp: ViewPager

    @ViewById(R.id.profile_tab_layout)
    lateinit var mProfileTabLayout: TabLayout

    @AfterViews
    internal fun initTabs() {
        mProfileVp!!.adapter = ProfileViewPagerAdapter(fragmentManager!!)
        mProfileTabLayout!!.setupWithViewPager(mProfileVp)
    }
}
