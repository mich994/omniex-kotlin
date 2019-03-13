package omniex.nl.omniex.ui.app.auth.login;

import nl.omniex.omniexshopping.ui.base.BaseView;

public interface LoginView extends BaseView {
    void onLoginSuccess();
    void onLoginErrorMessage(String message);
}
