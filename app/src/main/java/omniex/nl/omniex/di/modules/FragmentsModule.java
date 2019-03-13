package omniex.nl.omniex.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nl.omniex.omniexshopping.ui.app.about.AboutFragment_;
import nl.omniex.omniexshopping.ui.app.categories.CategoriesFragment_;
import nl.omniex.omniexshopping.ui.app.main.home.HomeFragment_;
import nl.omniex.omniexshopping.ui.app.newsletter.NewsletterFragment_;
import nl.omniex.omniexshopping.ui.app.order.complete.OrderCompleteFragment_;
import nl.omniex.omniexshopping.ui.app.order.overview.OrderOverviewFragment_;
import nl.omniex.omniexshopping.ui.app.order.payment.OrderPaymentFragment_;
import nl.omniex.omniexshopping.ui.app.order.payment.method.OrderPaymentMethodsFragment_;
import nl.omniex.omniexshopping.ui.app.order.shipping.OrderShippingFragment_;
import nl.omniex.omniexshopping.ui.app.order.shipping.method.OrderShippingMethodFragment_;
import nl.omniex.omniexshopping.ui.app.product.ProductDetailsFragment_;
import nl.omniex.omniexshopping.ui.app.product.list.ProductsListFragment_;
import nl.omniex.omniexshopping.ui.app.profile.ProfileFragment_;
import nl.omniex.omniexshopping.ui.app.profile.address.ProfileAddressFragment_;
import nl.omniex.omniexshopping.ui.app.profile.order.ProfileOrderFragment_;
import nl.omniex.omniexshopping.ui.views.dialogs.address.CountryDialog_;
import nl.omniex.omniexshopping.ui.views.dialogs.address.ZoneDialog_;
import nl.omniex.omniexshopping.ui.views.dialogs.cart.CartDialog_;

@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    public abstract HomeFragment_ contributeHomeFragment();

    @ContributesAndroidInjector
    public abstract CategoriesFragment_ contributeCategoriesFragment();

    @ContributesAndroidInjector
    public abstract ProductDetailsFragment_ contributeProductDetailsFragment();

    @ContributesAndroidInjector
    public abstract CartDialog_ contributeCartDialog();

    @ContributesAndroidInjector
    public abstract ProductsListFragment_ contributeProductsListFragment();

    @ContributesAndroidInjector
    public abstract ProfileFragment_ contributeProfileFragment();

    @ContributesAndroidInjector
    public abstract ProfileAddressFragment_ contributeProfileAddressFragment();

    @ContributesAndroidInjector
    public abstract ProfileOrderFragment_ contributeProfileOrderFragment();

    @ContributesAndroidInjector
    public abstract CountryDialog_ contributeCountryDialog();

    @ContributesAndroidInjector
    public abstract ZoneDialog_ contributeZoneDialog();

    @ContributesAndroidInjector
    public abstract OrderShippingFragment_ contributeOrderShippingFragment();

    @ContributesAndroidInjector
    public abstract OrderPaymentFragment_ contributeOrderPaymentFragment();

    @ContributesAndroidInjector
    public abstract OrderShippingMethodFragment_ contributeOrderShippingMethodFragment();

    @ContributesAndroidInjector
    public abstract OrderPaymentMethodsFragment_ contributeOrderPaymentMethodsFragment();

    @ContributesAndroidInjector
    public abstract OrderOverviewFragment_ contributeOrderOverviewFragment();

    @ContributesAndroidInjector
    public abstract OrderCompleteFragment_ contributeOrderCompleteFragment();

    @ContributesAndroidInjector
    public abstract AboutFragment_ contributeAboutFragment();

    @ContributesAndroidInjector
    public abstract NewsletterFragment_ contributeNewsletterFragment();

}
