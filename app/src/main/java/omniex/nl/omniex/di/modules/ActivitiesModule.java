package omniex.nl.omniex.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nl.omniex.omniexshopping.ui.app.auth.login.LoginActivity_;
import nl.omniex.omniexshopping.ui.app.auth.register.RegisterActivity_;
import nl.omniex.omniexshopping.ui.app.main.MainMenuActivity_;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity_;
import nl.omniex.omniexshopping.ui.app.profile.address.edit.EditAddressActivity_;
import nl.omniex.omniexshopping.ui.app.splash.SplashActivity_;

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    public abstract MainMenuActivity_ contributeMainMenuActivity();

    @ContributesAndroidInjector
    public abstract SplashActivity_ contributeSplashActivity();

    @ContributesAndroidInjector
    public abstract LoginActivity_ contributeLoginActivity();

    @ContributesAndroidInjector
    public abstract RegisterActivity_ contributeRegisterActivity();

    @ContributesAndroidInjector
    public abstract EditAddressActivity_ contributeEditAddressActivity();

    @ContributesAndroidInjector
    public abstract OrderActivity_ contributeOrderActivity();

}
