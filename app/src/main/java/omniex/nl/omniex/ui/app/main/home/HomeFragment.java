package omniex.nl.omniex.ui.app.main.home;

import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.products.FeaturedProduct;
import nl.omniex.omniexshopping.ui.adapters.FeatureProductsAdapter;
import nl.omniex.omniexshopping.ui.app.product.ProductDetailsFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, BaseRecyclerAdapter.ItemClickListener<FeaturedProduct> {

    @ViewById(R.id.home_featured_products_rv)
    RecyclerView mFeaturedProductsRv;

    @Inject
    FeatureProductsAdapter mFeatureProductsAdapter;

    @AfterViews
    void initFeaturedProducts(){
        mFeatureProductsAdapter.setItemClickListener(this);
        mFeaturedProductsRv.setAdapter(mFeatureProductsAdapter);
        if(mFeatureProductsAdapter.getItemCount()==0)
        getPresenter().getListOfFeatured();
        getPresenter().getBestseller();
    }

    @Override
    public void onFeaturedProductsFetched(List<FeaturedProduct> featuredProducts) {
        mFeatureProductsAdapter.setItems(featuredProducts);
    }

    @Override
    public void onItemClick(FeaturedProduct featuredProduct) {
        goToFragment(ProductDetailsFragment_.builder().mProductId(featuredProduct.getProductId()).build(), true);
    }
}
