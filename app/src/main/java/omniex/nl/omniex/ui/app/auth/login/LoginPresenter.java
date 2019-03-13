package omniex.nl.omniex.ui.app.auth.login;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.auth.Login;
import nl.omniex.omniexshopping.data.repository.ProfileRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;
import timber.log.Timber;

public class LoginPresenter extends BasePresenter<LoginView> {

    private ProfileRepository mProfileRepository;

    @Inject
    LoginPresenter(ProfileRepository profileRepository) {
        mProfileRepository = profileRepository;
    }

    void login(Login login) {
        addDisposable(mProfileRepository
                .login(login)
                .subscribe(voidResponse -> {
                    if (voidResponse.code() == 200) {
                        SharedPrefUtils.setUserGuest(false);
                        SharedPrefUtils.setUserLogged(true);
                        SharedPrefUtils.setTaxValue(Integer.valueOf(voidResponse.body().getProfile().getAccountCustomField().getTaxNumber()));
                        SharedPrefUtils.setNewsletterStatus(voidResponse.body().getProfile().getNewsletter().equals("1"));
                        ifViewAttached(LoginView::onLoginSuccess);
                    } else if (voidResponse.code() == 403) {
                        ifViewAttached(view -> view.onLoginErrorMessage("Email and/or password are not correct."));
                    } else {
                        ifViewAttached(view -> view.onLoginErrorMessage("Something went wrong, please try again."));
                    }
                }, error -> {
                    ifViewAttached(view -> view.onLoginErrorMessage("Please check your connection. If problem still occurs, please let us know by form on omniex.nl"));
                    error.printStackTrace();
                    Timber.e(error);
                }));
    }
}
