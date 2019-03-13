package omniex.nl.omniex.ui.app.main;

import android.app.AlertDialog;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.MenuItem;
import nl.omniex.omniexshopping.data.model.cart.Cart;
import nl.omniex.omniexshopping.data.model.cart.CartItemDelete;
import nl.omniex.omniexshopping.data.model.cart.CartQuantitySetter;
import nl.omniex.omniexshopping.ui.app.about.AboutFragment_;
import nl.omniex.omniexshopping.ui.app.auth.StartActivity_;
import nl.omniex.omniexshopping.ui.app.categories.CategoriesFragment_;
import nl.omniex.omniexshopping.ui.app.main.home.HomeFragment_;
import nl.omniex.omniexshopping.ui.app.newsletter.NewsletterFragment_;
import nl.omniex.omniexshopping.ui.app.order.OrderActivity_;
import nl.omniex.omniexshopping.ui.app.product.list.ProductsListFragment_;
import nl.omniex.omniexshopping.ui.app.profile.ProfileFragment_;
import nl.omniex.omniexshopping.ui.base.menu.BaseMenuActivity;
import nl.omniex.omniexshopping.ui.base.menu.MenuAdapter;
import nl.omniex.omniexshopping.ui.views.dialogs.cart.CartDialog;
import nl.omniex.omniexshopping.ui.views.dialogs.cart.CartDialog_;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;

@EActivity(R.layout.activity_main_menu)
public class MainMenuActivity extends BaseMenuActivity<MenuAdapter, MainMenuView, MainMenuPresenter> implements MainMenuView, CartDialog.OnUpdateItemQuantityListener, CartDialog.OnMakeOrderClickListener {

    @ViewById(R.id.duo_view_menu_background)
    ImageView mDuoDrawerLayout;

    private CartDialog mCartDialog;
    private boolean mIsCartOpen;

    @AfterViews
    void initHomeFragment() {
        mDuoDrawerLayout.setBackgroundResource(R.color.colorPrimaryDark);
        goToFragment(HomeFragment_.builder().build(), true, "");
    }

    @Override
    protected void onFirstCreate() {
        super.onFirstCreate();
        setToolbar();
    }

    @Override
    protected int getOverlayRes() {
        return R.id.menu_overlay;
    }

    @Override
    public int getToolbarId() {
        return 0;
    }

    @Override
    public int getDuoDrawerLayout() {
        return R.id.home_drawer;
    }

    @Override
    public int getStartIndex() {
        return 0;
    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        if (objectClicked != null) {
            MenuItem menuItem = (MenuItem) objectClicked;
            switch (position) {
                case 0:
                    goToFragment(HomeFragment_.builder().build(), false, "");
                    break;
                case 1:
                    goToFragment(ProductsListFragment_.builder().mIsBestSellersList(true).build(), false, "");
                    break;
                case 2:
                    goToFragment(CategoriesFragment_.builder().build(), false, "");
                    break;
                case 3:
                    handleProfileClick();
                    break;
                case 4:
                    handleNewsletterClick();
                    break;
                case 5:
                    goToFragment(AboutFragment_.builder().build(), false, "");
                    break;
                case 6:
                    handleLogoutClick();
                    break;
            }
        }
    }

    @Override
    public MenuAdapter createAdapter() {
        List<MenuItem> menuItems = new ArrayList<>();

        if (SharedPrefUtils.isUserLogged()) {
            menuItems.add(new MenuItem("Featured Products"));
            menuItems.add(new MenuItem("Bestsellers"));
            menuItems.add(new MenuItem("Products"));
            menuItems.add(new MenuItem("Profile"));
            menuItems.add(new MenuItem("Newsletter"));
            menuItems.add(new MenuItem("About Omniex"));
            menuItems.add(new MenuItem("Logout"));
        } else {
            menuItems.add(new MenuItem("Featured Products"));
            menuItems.add(new MenuItem("Bestsellers"));
            menuItems.add(new MenuItem("Products"));
            menuItems.add(new MenuItem("Profile"));
            menuItems.add(new MenuItem("Newsletter"));
            menuItems.add(new MenuItem("About Omniex"));
            menuItems.add(new MenuItem("Login"));
        }
        return new MenuAdapter(menuItems);
    }

    private void handleLogoutClick() {
        if (SharedPrefUtils.isUserLogged()) {
            getPresenter().logout();
        } else {
            finish();
            StartActivity_.intent(this).start();
        }
    }

    private void handleProfileClick() {
        if (SharedPrefUtils.isUserLogged()) {
            goToFragment(ProfileFragment_.builder().build(), false, "");
        } else {
            showGuestWarnDialog();
        }
    }

    private void handleNewsletterClick() {
        if (SharedPrefUtils.isUserLogged()) {
            goToFragment(NewsletterFragment_.builder().build(), false, "");
        } else {
            showGuestWarnDialog();
        }
    }

    public void showGuestWarnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage("Please log in to get access to this function.")
                .setPositiveButton("Login",
                        ((dialog, which) -> {
                            finish();
                            dialog.dismiss();
                            StartActivity_.intent(this).start();
                        }))
                .setNegativeButton("Later", ((dialog, which) -> {
                    dialog.dismiss();
                }))
                .show();
    }

    @Override
    public void onBackPressed() {
        toggleMenu();
    }

    private void setToolbar() {
        getCustomToolbar()
                .setIconEnd(R.drawable.twotone_shopping_cart_black_36)
                .setIconEndClickListener(() -> getPresenter().getCart());
    }

    @Override
    public void onCartFetched(Cart cart) {
        if (SharedPrefUtils.isUserLogged()) {
            if (!mIsCartOpen) {
                mCartDialog = CartDialog_.builder().mCart(cart).build().setOnUpdateItemQuantityListener(this).setOnMakeOrderClickListener(this);
                mCartDialog.show(getSupportFragmentManager(), "dialog");
                mIsCartOpen = true;
            } else {
                mCartDialog.refreshCart(cart);
            }
        } else {
            showGuestWarnDialog();
        }
    }

    @Override
    public void onMakeOrderClick() {
        OrderActivity_.intent(this).start();
    }

    @Override
    public void onCartDismiss() {
        mIsCartOpen = false;
    }

    @Override
    public void onUpdateQuantity(String productKey, int quantity) {
        getPresenter().updateCartQuantity(new CartQuantitySetter(productKey, quantity));
    }

    @Override
    public void onRemoveCartItem(CartItemDelete cartItemDelete) {
        getPresenter().deleteCartItem(cartItemDelete);
    }

    @Override
    public void onCartItemQuantityUpdated() {
        getPresenter().getCart();
    }

    @Override
    public void onCartEmpty() {
        if (SharedPrefUtils.isUserLogged()) {
            if (mCartDialog != null && mCartDialog.isVisible())
                mCartDialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage("Cart is empty.").setPositiveButton("OK", ((dialog, which) -> dialog.dismiss()));
            builder.show();
        } else {
            showGuestWarnDialog();
        }
    }

    @Override
    public void onLogoutSuccess() {
        finishAffinity();
        StartActivity_.intent(this).start();
    }

    @Override
    public void startLoading() {
        showProgressBar();
    }

    @Override
    public void stopLoading() {
        hideProgressBar();
    }
}
