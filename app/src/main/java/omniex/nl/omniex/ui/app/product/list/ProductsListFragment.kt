package omniex.nl.omniex.ui.app.product.list

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.adapters.ProductsListAdapter
import omniex.nl.omniex.ui.app.product.ProductDetailsFragment_
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg
import org.androidannotations.annotations.ViewById
import javax.inject.Inject

@EFragment(R.layout.fragment_products_list)
open class ProductsListFragment : BaseFragment<ProductsListView, ProductsListPresenter>(), ProductsListView, BaseRecyclerAdapter.ItemClickListener<Product> {

    @ViewById(R.id.products_list_rv)
    lateinit var mProductsRv: RecyclerView

    @Inject
    lateinit var mProductsListAdapter: ProductsListAdapter

    @FragmentArg
    lateinit var mCategoryId: Integer

    @JvmField
    @FragmentArg
    var mIsBestSellersList: Boolean? = null

    @AfterViews
    internal fun initProductsList() {
        mProductsListAdapter!!.setClickListener(this)
        mProductsRv!!.adapter = mProductsListAdapter
        if (mIsBestSellersList!!)
            getPresenter().getBestsellers()
        else
            getPresenter().getProductsList(mCategoryId.toInt())
    }

    override fun onProductsListFetched(productList: List<Product>) {
        if (productList.isEmpty()) {
            val builder = AlertDialog.Builder(getContext())
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                        fragmentManager!!.popBackStack()
                    }
                    .setMessage("Stay tuned. We will add products in this category soon.")
            builder.show()
        }
        mProductsListAdapter!!.setItems(productList)
    }

    override fun onItemClick(product: Product?) {
        goToFragment(ProductDetailsFragment_.builder().mProductId(product!!.productId!!).build(), true)
    }

}
