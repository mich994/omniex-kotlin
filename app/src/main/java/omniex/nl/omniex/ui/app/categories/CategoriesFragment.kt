package omniex.nl.omniex.ui.app.categories

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.Category
import omniex.nl.omniex.ui.adapters.CategoriesAdapter
import omniex.nl.omniex.ui.app.product.list.ProductsListFragment_
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_categories)
open class CategoriesFragment : BaseFragment<CategoriesView, CategoriesPresenter>(), CategoriesView, BaseRecyclerAdapter.ItemClickListener<Category> {

    @ViewById(R.id.categories_rv)
    lateinit var mCategoriesRv: RecyclerView

    @Inject
    lateinit var mCategoriesAdapter: CategoriesAdapter

    @AfterViews
    internal fun initCategoriesList() {
        mCategoriesAdapter!!.setClickListener(this)
        mCategoriesRv!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mCategoriesRv!!.adapter = mCategoriesAdapter
        getPresenter().getCategories()
    }

    override fun onCategoriesFetched(categoryList: List<Category>) {
        mCategoriesAdapter!!.setItems(categoryList)
    }

    override fun onItemClick(category: Category?) {
        goToFragment(ProductsListFragment_.builder().mIsBestSellersList(false).mCategoryId(category!!.categoryId).build(), true)
    }
}
