package omniex.nl.omniex.ui.app.splash;

import nl.omniex.omniexshopping.ui.base.BaseView;

public interface SplashView extends BaseView {

    void onTokenFetched();

    void onTokenError(String error);
}
