package omniex.nl.omniex.ui.app.splash;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.ui.app.auth.StartActivity_;
import nl.omniex.omniexshopping.ui.app.main.MainMenuActivity_;
import nl.omniex.omniexshopping.ui.base.BaseActivity;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {

    @AfterViews
    void getAccessToken() {
        getPresenter().resolveAccessToken();
    }

    @Override
    public void onTokenFetched() {
        finish();
        if (SharedPrefUtils.isUserLogged())
            MainMenuActivity_.intent(this).start();
        else
            StartActivity_.intent(this).start();
    }

    @Override
    public void onTokenError(String error) {
        showToastMessage(error, 1);
    }
}
