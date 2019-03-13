package omniex.nl.omniex.ui.app.product.list;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.products.Product;
import nl.omniex.omniexshopping.ui.adapters.ProductsListAdapter;
import nl.omniex.omniexshopping.ui.app.product.ProductDetailsFragment_;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.ui.base.BaseRecyclerAdapter;

@EFragment(R.layout.fragment_products_list)
public class ProductsListFragment extends BaseFragment<ProductsListView, ProductsListPresenter> implements ProductsListView, BaseRecyclerAdapter.ItemClickListener<Product> {

    @ViewById(R.id.products_list_rv)
    RecyclerView mProductsRv;

    @Inject
    ProductsListAdapter mProductsListAdapter;

    @FragmentArg
    Integer mCategoryId;

    @FragmentArg
    Boolean mIsBestSellersList;

    @AfterViews
    void initProductsList() {
        mProductsListAdapter.setItemClickListener(this);
        mProductsRv.setAdapter(mProductsListAdapter);
        if (mIsBestSellersList)
            getPresenter().getBestsellers();
        else
            getPresenter().getProductsList(mCategoryId);
    }

    @Override
    public void onProductsListFetched(List<Product> productList) {
        if (productList.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setPositiveButton("OK", ((dialog, which) -> {
                        dialog.dismiss();
                        getFragmentManager().popBackStack();
                    }))
                    .setMessage("Stay tuned. We will add products in this category soon.");
            builder.show();
        }
        mProductsListAdapter.setItems(productList);
    }

    @Override
    public void onItemClick(Product product) {
        goToFragment(ProductDetailsFragment_.builder().mProductId(product.getProductId()).build(), true);
    }

}
