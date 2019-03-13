package omniex.nl.omniex.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import nl.omniex.omniexshopping.ui.adapters.AddressesAdapter;
import nl.omniex.omniexshopping.ui.adapters.AddressesAdapter_;
import nl.omniex.omniexshopping.ui.adapters.CartAdapter;
import nl.omniex.omniexshopping.ui.adapters.CartAdapter_;
import nl.omniex.omniexshopping.ui.adapters.CategoriesAdapter;
import nl.omniex.omniexshopping.ui.adapters.CategoriesAdapter_;
import nl.omniex.omniexshopping.ui.adapters.CountriesAdapter;
import nl.omniex.omniexshopping.ui.adapters.CountriesAdapter_;
import nl.omniex.omniexshopping.ui.adapters.FeatureProductsAdapter;
import nl.omniex.omniexshopping.ui.adapters.FeatureProductsAdapter_;
import nl.omniex.omniexshopping.ui.adapters.OrderAddressesAdapter;
import nl.omniex.omniexshopping.ui.adapters.OrderAddressesAdapter_;
import nl.omniex.omniexshopping.ui.adapters.OrderStatutesAdapter;
import nl.omniex.omniexshopping.ui.adapters.OrderStatutesAdapter_;
import nl.omniex.omniexshopping.ui.adapters.ProductDetailsImageAdapter;
import nl.omniex.omniexshopping.ui.adapters.ProductDetailsImageAdapter_;
import nl.omniex.omniexshopping.ui.adapters.ProductsListAdapter;
import nl.omniex.omniexshopping.ui.adapters.ProductsListAdapter_;
import nl.omniex.omniexshopping.ui.adapters.ShippingMethodsAdapter;
import nl.omniex.omniexshopping.ui.adapters.ShippingMethodsAdapter_;
import nl.omniex.omniexshopping.ui.adapters.ZonesAdapter;
import nl.omniex.omniexshopping.ui.adapters.ZonesAdapter_;

@Module
public class AdaptersModule {

    @Provides
    FeatureProductsAdapter provideFeaturedProductsAdapter(Context context){
        return FeatureProductsAdapter_.getInstance_(context);
    }

    @Provides
    ProductDetailsImageAdapter provideProductDetailsImageAdapter(Context context){
        return ProductDetailsImageAdapter_.getInstance_(context);
    }

    @Provides
    CategoriesAdapter provideCategoriesAdapter(Context context){
        return CategoriesAdapter_.getInstance_(context);
    }

    @Provides
    CartAdapter provideCartAdapter(Context context){
        return CartAdapter_.getInstance_(context);
    }

    @Provides
    ProductsListAdapter provideProductsListAdapter(Context context){
        return ProductsListAdapter_.getInstance_(context);
    }

    @Provides
    CountriesAdapter provideCountriesAdapter(Context context){
        return CountriesAdapter_.getInstance_(context);
    }

    @Provides
    ZonesAdapter provideZonesAdapter(Context context){
        return ZonesAdapter_.getInstance_(context);
    }

    @Provides
    AddressesAdapter provideAddressesAdapter(Context context){
        return AddressesAdapter_.getInstance_(context);
    }

    @Provides
    OrderAddressesAdapter provideOrderAddressesAdapter(Context context){
        return OrderAddressesAdapter_.getInstance_(context);
    }

    @Provides
    ShippingMethodsAdapter provideShippingMethodsAdapter(Context context){
        return ShippingMethodsAdapter_.getInstance_(context);
    }

    @Provides
    OrderStatutesAdapter provideOrderStatutesAdapter(Context context){
        return OrderStatutesAdapter_.getInstance_(context);
    }
}
