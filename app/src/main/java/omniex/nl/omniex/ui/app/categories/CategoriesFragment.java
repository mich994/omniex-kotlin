package omniex.nl.omniex.ui.app.categories;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.products.Category;
import nl.omniex.omniexshopping.ui.adapters.CategoriesAdapter;
import nl.omniex.omniexshopping.ui.app.product.list.ProductsListFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter;

@EFragment(R.layout.fragment_categories)
public class CategoriesFragment extends BaseFragment<CategoriesView, CategoriesPresenter> implements CategoriesView, BaseRecyclerAdapter.ItemClickListener<Category> {

    @ViewById(R.id.categories_rv)
    RecyclerView mCategoriesRv;

    @Inject
    CategoriesAdapter mCategoriesAdapter;

    @AfterViews
    void initCategoriesList(){
        mCategoriesAdapter.setItemClickListener(this);
        mCategoriesRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mCategoriesRv.setAdapter(mCategoriesAdapter);
        getPresenter().getCategories();
    }

    @Override
    public void onCategoriesFetched(List<Category> categoryList) {
        mCategoriesAdapter.setItems(categoryList);
    }

    @Override
    public void onItemClick(Category category) {
        goToFragment(ProductsListFragment_.builder().mIsBestSellersList(false).mCategoryId(category.getCategoryId()).build(), true);
    }
}
