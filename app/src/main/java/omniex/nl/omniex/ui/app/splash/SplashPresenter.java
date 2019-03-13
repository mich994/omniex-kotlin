package omniex.nl.omniex.ui.app.splash;

import java.util.Objects;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.auth.OldToken;
import nl.omniex.omniexshopping.data.model.response.AccessTokenResponse;
import nl.omniex.omniexshopping.data.repository.TokenRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;
import retrofit2.Response;
import timber.log.Timber;

public class SplashPresenter extends BasePresenter<SplashView> {

    private TokenRepository mTokenRepository;
    private SharedPrefUtils mSharedPrefUtils;

    @Inject
    SplashPresenter(TokenRepository tokenRepository, SharedPrefUtils sharedPrefUtils) {
        mTokenRepository = tokenRepository;
        mSharedPrefUtils = sharedPrefUtils;
    }

    void resolveAccessToken() {
        if (mSharedPrefUtils.getAccessToken().isEmpty())
            getAccessToken();
        else
            refreshAccessToken();
    }

    private void getAccessToken() {
        addDisposable(mTokenRepository
                .getAccessToken()
                .subscribe(
                        this::handleTokenResponse,
                        error -> {
                            ifViewAttached(view -> view.onTokenError(error.getMessage()));
                            Timber.e(error);
                        })
        );
    }

    private void refreshAccessToken() {
        addDisposable(mTokenRepository
                .refreshAccessToken(new OldToken(mSharedPrefUtils.getOldToken()))
                .subscribe(
                        this::handleTokenResponse,
                        error -> {
                            ifViewAttached(view -> view.onTokenError(error.getMessage()));
                            Timber.e(error);
                        })
        );
    }

    private void handleTokenResponse(Response<AccessTokenResponse> tokenResponse) {
        if (tokenResponse.isSuccessful() && tokenResponse.code() == 200) {
            mSharedPrefUtils.saveAccessToken(Objects.requireNonNull(tokenResponse.body()).getAccessToken().getAccessToken());
            mSharedPrefUtils.saveOldToken(Objects.requireNonNull(tokenResponse.body()).getAccessToken().getToken());
            ifViewAttached(SplashView::onTokenFetched);
        } else
            ifViewAttached(view -> view.onTokenError(tokenResponse.message()));
    }
}
