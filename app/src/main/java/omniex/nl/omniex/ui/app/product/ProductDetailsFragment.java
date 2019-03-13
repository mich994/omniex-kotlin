package omniex.nl.omniex.ui.app.product;

import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.cart.AddToCartModel;
import nl.omniex.omniexshopping.data.model.products.Product;
import nl.omniex.omniexshopping.ui.adapters.DetailsImagePagerAdapter;
import nl.omniex.omniexshopping.ui.app.main.MainMenuActivity;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;

@EFragment(R.layout.fragment_product_details)
public class ProductDetailsFragment extends BaseFragment<ProductDetailsView, ProductDetailsPresenter> implements ProductDetailsView {

    @ViewById(R.id.product_details_images_vp)
    ViewPager mDetailsImagesVp;

    @ViewById(R.id.product_details_images_indicator)
    CircleIndicator mCircleIndicator;

    @ViewById(R.id.product_details_price_tv)
    TextView mPriceTv;

    @ViewById(R.id.product_details_quantity_count)
    TextView mQuantityCountTv;

    @ViewById(R.id.product_details_description_tv)
    TextView mDescriptionTv;

    @ViewById(R.id.product_details_add_to_cart_btn)
    ImageButton mAddToCartButton;

    @FragmentArg
    Integer mProductId;

    private DetailsImagePagerAdapter mDetailsImagePagerAdapter;
    private int mQuantity = 1;
    private MainMenuActivity mMainMenuActivity;
    private List<String> mImageUrlList = new ArrayList<>();

    @AfterViews
    void initializeDetails() {
        mMainMenuActivity = (MainMenuActivity) getActivity();
        if (!SharedPrefUtils.isUserLogged())
            mAddToCartButton.setAlpha(.5f);
        getPresenter().getProductDetails(mProductId);
    }

    private void initViewPager(List<String> imagesUrl) {
        mDetailsImagePagerAdapter = new DetailsImagePagerAdapter(getFragmentManager(), imagesUrl);
        mDetailsImagesVp.setAdapter(mDetailsImagePagerAdapter);
        mCircleIndicator.setViewPager(mDetailsImagesVp);
    }

    @Click({R.id.product_details_add_to_cart_btn, R.id.product_details_quantity_add, R.id.product_details_quantity_remove})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_details_add_to_cart_btn:
                handleAddToCartClick();
                break;
            case R.id.product_details_quantity_add:
                handleQuantity(true);
                break;
            case R.id.product_details_quantity_remove:
                handleQuantity(false);
                break;
        }
    }

    private void handleAddToCartClick() {
        if (SharedPrefUtils.isUserLogged())
            getPresenter().addToCart(new AddToCartModel(String.valueOf(mProductId), String.valueOf(mQuantity)));
        else {
            mMainMenuActivity.showGuestWarnDialog();
        }
    }

    private void handleQuantity(boolean increase) {
        if (!increase && mQuantity - 1 > 0) {
            mQuantity -= 1;
            mQuantityCountTv.setText(String.valueOf(mQuantity));
        } else if (increase) {
            mQuantity += 1;
            mQuantityCountTv.setText(String.valueOf(mQuantity));
        }
    }

    @Override
    public void onDetailsFetched(Product product) {
        if((product.getImageUrlsList()==null ||product.getImageUrlsList().isEmpty()) && product.getImageUrl()!=null){
            mImageUrlList.add(product.getImageUrl());
        }else {
            mImageUrlList.addAll(product.getImageUrlsList());
        }
        initViewPager(mImageUrlList);
        mPriceTv.setText(product.getPriceExcTaxFormated());
        mDescriptionTv.setText(Html.fromHtml(product.getDescription()));
    }

    @Override
    public void startLoading() {
        getBaseActivity().startLoading();
    }

    @Override
    public void stopLoading() {
        getBaseActivity().stopLoading();
    }
}
