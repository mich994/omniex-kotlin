package omniex.nl.omniex.ui.adapters

import android.view.ViewGroup
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartProduct
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.ui.views.recycleritems.CartItemView
import omniex.nl.omniex.ui.views.recycleritems.CartItemView_
import org.androidannotations.annotations.EBean

@EBean
open class CartAdapter : BaseRecyclerAdapter<CartProduct, CartItemView>() {

    lateinit var mOnCartItemClickListener: OnCartItemClickListener
    private var mIsFromOverview: Boolean = false

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): CartItemView {
        return CartItemView_.build(parent.context)
    }

    override fun onBindViewHolder(holder: ViewWrapper<CartItemView>, position: Int) {
        holder.view
                .bind(getItem(position), position, mIsFromOverview)
                .setOnCartItemClickListener(mOnCartItemClickListener)
    }

    fun setFromOverview(fromOverview: Boolean) {
        mIsFromOverview = fromOverview
    }

    fun setOnCartItemClickListener(onCartItemClickListener: OnCartItemClickListener) {
        mOnCartItemClickListener = onCartItemClickListener
    }

    interface OnCartItemClickListener {
        fun onUpdateItemQuantity(productKey: String?, quantity: Int?)
        fun onRemoveItem(cartItemDelete: CartItemDelete?)
    }

}
