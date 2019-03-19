package omniex.nl.omniex.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import omniex.nl.omniex.ui.adapters.*

@Module
class AdaptersModule {

    @Provides
     fun provideFeaturedProductsAdapter(context: Context): FeatureProductsAdapter {
        return FeatureProductsAdapter_.getInstance_(context)
    }

    @Provides
     fun provideProductDetailsImageAdapter(context: Context): ProductDetailsImageAdapter {
        return ProductDetailsImageAdapter_.getInstance_(context)
    }

    @Provides
     fun provideCategoriesAdapter(context: Context): CategoriesAdapter {
        return CategoriesAdapter_.getInstance_(context)
    }

    @Provides
     fun provideCartAdapter(context: Context): CartAdapter {
        return CartAdapter_.getInstance_(context)
    }

    @Provides
     fun provideProductsListAdapter(context: Context): ProductsListAdapter {
        return ProductsListAdapter_.getInstance_(context)
    }

    @Provides
     fun provideCountriesAdapter(context: Context): CountriesAdapter {
        return CountriesAdapter_.getInstance_(context)
    }

    @Provides
     fun provideZonesAdapter(context: Context): ZonesAdapter {
        return ZonesAdapter_.getInstance_(context)
    }

    @Provides
     fun provideAddressesAdapter(context: Context): AddressesAdapter {
        return AddressesAdapter_.getInstance_(context)
    }

    @Provides
     fun provideOrderAddressesAdapter(context: Context): OrderAddressesAdapter {
        return OrderAddressesAdapter_.getInstance_(context)
    }

    @Provides
     fun provideShippingMethodsAdapter(context: Context): ShippingMethodsAdapter {
        return ShippingMethodsAdapter_.getInstance_(context)
    }

    @Provides
     fun provideOrderStatutesAdapter(context: Context): OrderStatutesAdapter {
        return OrderStatutesAdapter_.getInstance_(context)
    }
}
