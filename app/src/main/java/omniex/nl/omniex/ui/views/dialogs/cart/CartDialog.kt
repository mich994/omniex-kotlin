package omniex.nl.omniex.ui.views.dialogs.cart

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.DialogFragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import dagger.android.support.AndroidSupportInjection
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.cart.Cart
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.ui.adapters.CartAdapter
import org.androidannotations.annotations.*
import javax.inject.Inject

@EFragment(R.layout.fragment_cart)
open class CartDialog : DialogFragment(), CartAdapter.OnCartItemClickListener {

    @ViewById(R.id.cart_dialog_items_rv)
    lateinit var mCartItemsRv: RecyclerView

    @ViewById(R.id.cart_dialog_total_price)
    lateinit var mTotalPrice: TextView

    @Inject
    lateinit var mCartAdapter: CartAdapter

    @FragmentArg
    lateinit var mCart: Cart

    private var mOnUpdateItemQuantityListener: OnUpdateItemQuantityListener? = null
    private var mOnMakeOrderClickListener: OnMakeOrderClickListener? = null

    override fun onResume() {
        super.onResume()
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layoutParams
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    @AfterViews
    internal fun setViews() {
        isCancelable = false
        mCartAdapter!!.setFromOverview(false)
        mCartAdapter!!.setOnCartItemClickListener(this)
        mCartItemsRv!!.adapter = mCartAdapter
        mTotalPrice!!.setText(mCart.total)
        mCartAdapter!!.setItems(mCart.cartProducts!!)
    }

    @AfterInject
    protected fun afterInject() {
        try {
            AndroidSupportInjection.inject(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    @Click(R.id.cart_dialog_continue_btn, R.id.cart_dialog_make_order_btn)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.cart_dialog_continue_btn -> {
                dismiss()
                mOnMakeOrderClickListener!!.onCartDismiss()
            }
            R.id.cart_dialog_make_order_btn -> {
                dismiss()
                mOnMakeOrderClickListener!!.onMakeOrderClick()
                mOnMakeOrderClickListener!!.onCartDismiss()
            }
        }
    }

    fun refreshCart(cart: Cart) {
        mCart = cart
        mTotalPrice!!.text = mCart.total
        mCartAdapter!!.setItems(mCart.cartProducts!!)
    }

    fun setOnMakeOrderClickListener(onMakeOrderClickListener: OnMakeOrderClickListener): CartDialog {
        mOnMakeOrderClickListener = onMakeOrderClickListener
        return this
    }

    fun setOnUpdateItemQuantityListener(onUpdateItemQuantityListener: OnUpdateItemQuantityListener): CartDialog {
        mOnUpdateItemQuantityListener = onUpdateItemQuantityListener
        return this
    }

    override fun onUpdateItemQuantity(productKey: String?, quantity: Int?) {
        mOnUpdateItemQuantityListener!!.onUpdateQuantity(productKey!!, quantity!!)
    }

   override fun onRemoveItem(cartItemDelete: CartItemDelete?) {
        mOnUpdateItemQuantityListener!!.onRemoveCartItem(cartItemDelete!!)
    }

    interface OnMakeOrderClickListener {
        fun onMakeOrderClick()
        fun onCartDismiss()
    }

    interface OnUpdateItemQuantityListener {
        fun onUpdateQuantity(productId: String, quantity: Int)
        fun onRemoveCartItem(cartItemDelete: CartItemDelete)
    }
}
