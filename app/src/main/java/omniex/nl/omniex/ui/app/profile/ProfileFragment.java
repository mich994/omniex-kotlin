package omniex.nl.omniex.ui.app.profile;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.ui.adapters.ProfileViewPagerAdapter;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends BaseFragment<ProfileView, ProfilePresenter> implements ProfileView {

    @ViewById(R.id.profile_vp)
    ViewPager mProfileVp;

    @ViewById(R.id.profile_tab_layout)
    TabLayout mProfileTabLayout;

    @AfterViews
    void initTabs(){
        mProfileVp.setAdapter(new ProfileViewPagerAdapter(getFragmentManager()));
        mProfileTabLayout.setupWithViewPager(mProfileVp);
    }
}
