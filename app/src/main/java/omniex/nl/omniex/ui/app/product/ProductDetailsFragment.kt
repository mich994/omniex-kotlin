package omniex.nl.omniex.ui.app.product

import android.support.v4.view.ViewPager
import android.text.Html
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import me.relex.circleindicator.CircleIndicator
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.cart.AddToCartModel
import omniex.nl.omniex.data.model.products.Product
import omniex.nl.omniex.ui.adapters.DetailsImagePagerAdapter
import omniex.nl.omniex.ui.app.main.MainMenuActivity
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.utils.SharedPrefUtils
import org.androidannotations.annotations.*
import java.util.*


@EFragment(R.layout.fragment_product_details)
open class ProductDetailsFragment : BaseFragment<ProductDetailsView, ProductDetailsPresenter>(), ProductDetailsView {

    @ViewById(R.id.product_details_images_vp)
    lateinit var mDetailsImagesVp: ViewPager

    @ViewById(R.id.product_details_images_indicator)
    lateinit var mCircleIndicator: CircleIndicator

    @ViewById(R.id.product_details_price_tv)
    lateinit var mPriceTv: TextView

    @ViewById(R.id.product_details_quantity_count)
    lateinit var mQuantityCountTv: TextView

    @ViewById(R.id.product_details_description_tv)
    lateinit var mDescriptionTv: TextView

    @ViewById(R.id.product_details_add_to_cart_btn)
    lateinit var mAddToCartButton: ImageButton

    @FragmentArg
    lateinit var mProductId: Integer

    private var mDetailsImagePagerAdapter: DetailsImagePagerAdapter? = null
    private var mQuantity = 1
    private var mMainMenuActivity: MainMenuActivity? = null
    private val mImageUrlList = ArrayList<String>()

    @AfterViews
    internal fun initializeDetails() {
        mMainMenuActivity = getActivity() as MainMenuActivity
        if (!SharedPrefUtils.isUserLogged())
            mAddToCartButton!!.alpha = .5f
        getPresenter().getProductDetails(mProductId.toInt())
    }

    private fun initViewPager(imagesUrl: List<String>) {
        mDetailsImagePagerAdapter = DetailsImagePagerAdapter(fragmentManager!!, imagesUrl)
        mDetailsImagesVp!!.adapter = mDetailsImagePagerAdapter
        mCircleIndicator!!.setViewPager(mDetailsImagesVp)
    }

    @Click(R.id.product_details_add_to_cart_btn, R.id.product_details_quantity_add, R.id.product_details_quantity_remove)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.product_details_add_to_cart_btn -> handleAddToCartClick()
            R.id.product_details_quantity_add -> handleQuantity(true)
            R.id.product_details_quantity_remove -> handleQuantity(false)
        }
    }

    private fun handleAddToCartClick() {
        if (SharedPrefUtils.isUserLogged())
            getPresenter().addToCart(AddToCartModel(mProductId.toString(), mQuantity.toString()))
        else {
            mMainMenuActivity!!.showGuestWarnDialog()
        }
    }

    private fun handleQuantity(increase: Boolean) {
        if (!increase && mQuantity - 1 > 0) {
            mQuantity -= 1
            mQuantityCountTv!!.text = mQuantity.toString()
        } else if (increase) {
            mQuantity += 1
            mQuantityCountTv!!.text = mQuantity.toString()
        }
    }

   override fun onDetailsFetched(product: Product) {
        if ((product.imageUrlsList == null || product.imageUrlsList!!.isEmpty()) && product.imageUrl!= null) {
            mImageUrlList.add(product.imageUrl!!)
        } else {
            mImageUrlList.addAll(product.imageUrlsList!!)
        }
        initViewPager(mImageUrlList)
       mPriceTv!!.text = product.priceExcTaxFormated
        mDescriptionTv!!.text = Html.fromHtml(product.description)
    }

    override fun startLoading() {
        baseActivity!!.startLoading()
    }

    override fun stopLoading() {
        baseActivity!!.stopLoading()
    }
}
