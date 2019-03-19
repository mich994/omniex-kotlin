package omniex.nl.omniex.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import omniex.nl.omniex.ui.app.about.AboutFragment_
import omniex.nl.omniex.ui.app.categories.CategoriesFragment_
import omniex.nl.omniex.ui.app.main.home.HomeFragment_
import omniex.nl.omniex.ui.app.newsletter.NewsletterFragment_
import omniex.nl.omniex.ui.app.order.complete.OrderCompleteFragment_
import omniex.nl.omniex.ui.app.order.overview.OrderOverviewFragment_
import omniex.nl.omniex.ui.app.order.payment.OrderPaymentFragment_
import omniex.nl.omniex.ui.app.order.payment.method.OrderPaymentMethodsFragment_
import omniex.nl.omniex.ui.app.order.shipping.OrderShippingFragment_
import omniex.nl.omniex.ui.app.order.shipping.method.OrderShippingMethodFragment_
import omniex.nl.omniex.ui.app.product.ProductDetailsFragment_
import omniex.nl.omniex.ui.app.product.list.ProductsListFragment_
import omniex.nl.omniex.ui.app.profile.ProfileFragment_
import omniex.nl.omniex.ui.app.profile.address.ProfileAddressFragment_
import omniex.nl.omniex.ui.app.profile.order.ProfileOrderFragment_
import omniex.nl.omniex.ui.views.dialogs.address.CountryDialog_
import omniex.nl.omniex.ui.views.dialogs.address.ZoneDialog_
import omniex.nl.omniex.ui.views.dialogs.cart.CartDialog_

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment_

    @ContributesAndroidInjector
    abstract fun contributeCategoriesFragment(): CategoriesFragment_

    @ContributesAndroidInjector
    abstract fun contributeProductDetailsFragment(): ProductDetailsFragment_

    @ContributesAndroidInjector
    abstract fun contributeCartDialog(): CartDialog_

    @ContributesAndroidInjector
    abstract fun contributeProductsListFragment(): ProductsListFragment_

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment_

    @ContributesAndroidInjector
    abstract fun contributeProfileAddressFragment(): ProfileAddressFragment_

    @ContributesAndroidInjector
    abstract fun contributeProfileOrderFragment(): ProfileOrderFragment_

    @ContributesAndroidInjector
    abstract fun contributeCountryDialog(): CountryDialog_

    @ContributesAndroidInjector
    abstract fun contributeZoneDialog(): ZoneDialog_

    @ContributesAndroidInjector
    abstract fun contributeOrderShippingFragment(): OrderShippingFragment_

    @ContributesAndroidInjector
    abstract fun contributeOrderPaymentFragment(): OrderPaymentFragment_

    @ContributesAndroidInjector
    abstract fun contributeOrderShippingMethodFragment(): OrderShippingMethodFragment_

    @ContributesAndroidInjector
    abstract fun contributeOrderPaymentMethodsFragment(): OrderPaymentMethodsFragment_

    @ContributesAndroidInjector
    abstract fun contributeOrderOverviewFragment(): OrderOverviewFragment_

    @ContributesAndroidInjector
    abstract fun contributeOrderCompleteFragment(): OrderCompleteFragment_

    @ContributesAndroidInjector
    abstract fun contributeAboutFragment(): AboutFragment_

    @ContributesAndroidInjector
    abstract fun contributeNewsletterFragment(): NewsletterFragment_

}
