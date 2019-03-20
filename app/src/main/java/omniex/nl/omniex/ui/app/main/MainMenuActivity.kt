package omniex.nl.omniex.ui.app.main

import android.support.v7.app.AlertDialog
import android.widget.ImageView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.MenuItem
import omniex.nl.omniex.data.model.cart.Cart
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartQuantitySetter
import omniex.nl.omniex.ui.app.about.AboutFragment_
import omniex.nl.omniex.ui.app.auth.StartActivity_
import omniex.nl.omniex.ui.app.categories.CategoriesFragment_
import omniex.nl.omniex.ui.app.main.home.HomeFragment_
import omniex.nl.omniex.ui.app.newsletter.NewsletterFragment_
import omniex.nl.omniex.ui.app.order.OrderActivity_
import omniex.nl.omniex.ui.app.product.list.ProductsListFragment_
import omniex.nl.omniex.ui.app.profile.ProfileFragment_
import omniex.nl.omniex.ui.base.menu.BaseMenuActivity
import omniex.nl.omniex.ui.base.menu.MenuAdapter
import omniex.nl.omniex.ui.base.menu.MenuHelper
import omniex.nl.omniex.ui.views.dialogs.cart.CartDialog
import omniex.nl.omniex.ui.views.dialogs.cart.CartDialog_
import omniex.nl.omniex.ui.views.toolbar.CustomToolbar
import omniex.nl.omniex.utils.SharedPrefUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import java.util.*
import javax.inject.Inject


@EActivity(R.layout.activity_main_menu)
open class MainMenuActivity : BaseMenuActivity<MenuAdapter, MainMenuView, MainMenuPresenter>(), MainMenuView, MenuHelper.MenuBase<MenuAdapter>, CartDialog.OnUpdateItemQuantityListener, CartDialog.OnMakeOrderClickListener {

    @ViewById(R.id.duo_view_menu_background)
    lateinit var mDuo: ImageView

       private var mCartDialog: CartDialog? = null
    private var mIsCartOpen: Boolean = false

    override val overlayRes: Int
        get() = R.id.menu_overlay

    override fun toolbarId(): Int = 0

    override fun duoDrawerLayout(): Int = R.id.home_drawer

    override fun startIndex(): Int = 0

    @Inject
    lateinit var mSharedPrefUtils: SharedPrefUtils

    @AfterViews
    internal fun initHomeFragment() {
        mDuo!!.setBackgroundResource(R.color.colorPrimaryDark)
        goToFragment(HomeFragment_.builder().build(), true, "")
    }

    override fun onFirstCreate() {
        super.onFirstCreate()
        setToolbar()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any) {
        if (objectClicked != null) {
            when (position) {
                0 -> goToFragment(HomeFragment_.builder().build(), false, "")
                1 -> goToFragment(ProductsListFragment_.builder().mIsBestSellersList(true).build(), false, "")
                2 -> goToFragment(CategoriesFragment_.builder().build(), false, "")
                3 -> handleProfileClick()
                4 -> handleNewsletterClick()
                5 -> goToFragment(AboutFragment_.builder().build(), false, "")
                6 -> handleLogoutClick()
            }
        }
    }

    override fun createAdapter(): MenuAdapter {
        val menuItems = ArrayList<MenuItem>()
        val isTrue= true

        if (isTrue) {
            menuItems.add(MenuItem("Featured Products"))
            menuItems.add(MenuItem("Bestsellers"))
            menuItems.add(MenuItem("Products"))
            menuItems.add(MenuItem("Profile"))
            menuItems.add(MenuItem("Newsletter"))
            menuItems.add(MenuItem("About Omniex"))
            menuItems.add(MenuItem("Logout"))
        } else {
            menuItems.add(MenuItem("Featured Products"))
            menuItems.add(MenuItem("Bestsellers"))
            menuItems.add(MenuItem("Products"))
            menuItems.add(MenuItem("Profile"))
            menuItems.add(MenuItem("Newsletter"))
            menuItems.add(MenuItem("About Omniex"))
            menuItems.add(MenuItem("Login"))
        }
        return MenuAdapter(menuItems)
    }
//
    private fun handleLogoutClick() {
        if (mSharedPrefUtils.isUserLogged()) {
            getPresenter().logout()
        } else {
            finish()
            StartActivity_.intent(this).start()
        }
    }

    private fun handleProfileClick() {
        if (mSharedPrefUtils.isUserLogged()) {
            goToFragment(ProfileFragment_.builder().build(), false, "")
        } else {
            showGuestWarnDialog()
        }
    }

    private fun handleNewsletterClick() {
        if (mSharedPrefUtils.isUserLogged()) {
            goToFragment(NewsletterFragment_.builder().build(), false, "")
        } else {
            showGuestWarnDialog()
        }
    }

    fun showGuestWarnDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
                .setMessage("Please log in to get access to this function.")
                .setPositiveButton("Login"
                ) { dialog, which ->
                    finish()
                    dialog.dismiss()
                    StartActivity_.intent(this).start()
                }
                .setNegativeButton("Later") { dialog, which -> dialog.dismiss() }
                .show()
    }

    override fun onBackPressed() {
        toggleMenu()
    }

    fun setToolbar() {
        customToolbar
                .setIconEnd(R.drawable.twotone_shopping_cart_black_36)
                .setIconEndClickListener(object : CustomToolbar.IconEndClickListner{
                    override fun onIconEndClick() {
                        getPresenter().getCart()
                    }
                })
    }

    override fun onCartFetched(cart: Cart) {
        if (mSharedPrefUtils.isUserLogged()) {
            if (!mIsCartOpen) {
                mCartDialog = CartDialog_.builder().mCart(cart).build().setOnUpdateItemQuantityListener(this).setOnMakeOrderClickListener(this)
                mCartDialog!!.show(getSupportFragmentManager(), "dialog")
                mIsCartOpen = true
            } else {
                mCartDialog!!.refreshCart(cart)
            }
        } else {
            showGuestWarnDialog()
        }
    }

   override fun onMakeOrderClick() {
        OrderActivity_.intent(this).start()
    }

    override fun onCartDismiss() {
        mIsCartOpen = false
    }

    override fun onUpdateQuantity(productKey: String, quantity: Int) {
        getPresenter().updateCartQuantity(CartQuantitySetter(productKey, quantity))
    }

    override fun onRemoveCartItem(cartItemDelete: CartItemDelete) {
        getPresenter().deleteCartItem(cartItemDelete)
    }

    override fun onCartItemQuantityUpdated() {
//        getPresenter().getCart()
    }

    override fun onCartEmpty() {
        if (mSharedPrefUtils.isUserLogged()) {
            if (mCartDialog != null && mCartDialog!!.isVisible())
                mCartDialog!!.dismiss()
            val builder = AlertDialog.Builder(this).setMessage("Cart is empty.").setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
            builder.show()
        } else {
            showGuestWarnDialog()
        }
    }

    override fun onLogoutSuccess() {
        finishAffinity()
        StartActivity_.intent(this).start()
    }
//
    override fun startLoading() {
        showProgressBar()
    }

    override fun stopLoading() {
        hideProgressBar()
    }
}
