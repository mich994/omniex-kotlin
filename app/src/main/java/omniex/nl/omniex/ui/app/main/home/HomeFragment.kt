package omniex.nl.omniex.ui.app.main.home

import android.support.v7.widget.RecyclerView

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

import javax.inject.Inject

import nl.omniex.omniexshopping.R
import nl.omniex.omniexshopping.data.model.products.FeaturedProduct
import nl.omniex.omniexshopping.ui.adapters.FeatureProductsAdapter
import nl.omniex.omniexshopping.ui.app.product.ProductDetailsFragment_
import nl.omniex.omniexshopping.ui.base.BaseFragment
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter

@EFragment(R.layout.fragment_home)
class HomeFragment : BaseFragment<HomeView, HomePresenter>(), HomeView, BaseRecyclerAdapter.ItemClickListener<FeaturedProduct> {

    @ViewById(R.id.home_featured_products_rv)
    internal var mFeaturedProductsRv: RecyclerView? = null

    @Inject
    internal var mFeatureProductsAdapter: FeatureProductsAdapter? = null

    @AfterViews
    internal fun initFeaturedProducts() {
        mFeatureProductsAdapter!!.setItemClickListener(this)
        mFeaturedProductsRv!!.adapter = mFeatureProductsAdapter
        if (mFeatureProductsAdapter!!.getItemCount() === 0)
            getPresenter().getListOfFeatured()
        getPresenter().getBestseller()
    }

    override fun onFeaturedProductsFetched(featuredProducts: List<FeaturedProduct>) {
        mFeatureProductsAdapter!!.setItems(featuredProducts)
    }

    fun onItemClick(featuredProduct: FeaturedProduct) {
        goToFragment(ProductDetailsFragment_.builder().mProductId(featuredProduct.getProductId()).build(), true)
    }
}
