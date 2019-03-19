package omniex.nl.omniex.ui.app.main.home

import android.support.v7.widget.RecyclerView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.FeaturedProduct
import omniex.nl.omniex.ui.adapters.FeatureProductsAdapter
import omniex.nl.omniex.ui.app.product.ProductDetailsFragment_
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject



@EFragment(R.layout.fragment_home)
open class HomeFragment : BaseFragment<HomeView, HomePresenter>(), HomeView, BaseRecyclerAdapter.ItemClickListener<FeaturedProduct> {

    @ViewById(R.id.home_featured_products_rv)
    lateinit var mFeaturedProductsRv: RecyclerView

    @Inject
    lateinit var mFeatureProductsAdapter: FeatureProductsAdapter

    @AfterViews
    internal fun initFeaturedProducts() {
        mFeatureProductsAdapter.setClickListener(this)
        mFeaturedProductsRv.adapter = mFeatureProductsAdapter
        if (mFeatureProductsAdapter.itemCount === 0)
            getPresenter().getListOfFeatured()
        getPresenter().getBestseller()
    }

    override fun onFeaturedProductsFetched(featuredProducts: List<FeaturedProduct>) {
        mFeatureProductsAdapter!!.setItems(featuredProducts)
    }

    override fun onItemClick(featuredProduct: FeaturedProduct?) {
        goToFragment(ProductDetailsFragment_.builder().mProductId(featuredProduct!!.productId).build(), true)
    }
}
