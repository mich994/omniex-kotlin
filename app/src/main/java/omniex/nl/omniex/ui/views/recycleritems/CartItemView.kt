package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartProduct
import omniex.nl.omniex.ui.adapters.CartAdapter
import omniex.nl.omniex.utils.StringUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_cart_item)
class CartItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.cart_item_image)
    lateinit var mCartImage: ImageView

    @ViewById(R.id.cart_item_name)
    lateinit var mItemName: TextView

    @ViewById(R.id.cart_item_price_per_one)
    lateinit var mPricePerOne: TextView

    @ViewById(R.id.cart_item_quantity_count)
    lateinit var mQuantityCount: TextView

    @ViewById(R.id.cart_item_total_price)
    lateinit var mTotalPrice: TextView

    @ViewById(R.id.cart_item_delete)
    lateinit var mDeleteItem: ImageView

    @ViewById(R.id.cart_item_quantity_add)
    lateinit var mQuantityAddIv: ImageView

    @ViewById(R.id.cart_item_quantity_remove)
    lateinit var mQuantityRemoveIv: ImageView

    private var mOnCartItemClickListener: CartAdapter.OnCartItemClickListener? = null
    private var mCartProduct: CartProduct? = null
    private var mQuantity: Int = 0
    private val mIsQuantityChanging: Boolean = false
    private var mPosition: Int = 0

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(cartProduct: CartProduct, position: Int, isFromOverview: Boolean): CartItemView {
        mCartProduct = cartProduct
        mPosition = position
        mQuantity = Integer.valueOf(cartProduct.quantity)
        mItemName!!.setText(mCartProduct!!.name)
        mPricePerOne!!.text = "Per one: " + mCartProduct!!.price
        mQuantityCount!!.text = mQuantity.toString()
        mTotalPrice!!.text = "Total price: " + mCartProduct!!.totalPrice
        Glide.with(this)
                .load(StringUtils.fixUrl(mCartProduct!!.thumb))
                .into(mCartImage!!)
        if (isFromOverview) {
            mQuantityAddIv!!.visibility = View.GONE
            mQuantityRemoveIv!!.visibility = View.GONE
            mDeleteItem!!.visibility = View.GONE
        }

        return this
    }

    fun setOnCartItemClickListener(onCartItemClickListener: CartAdapter.OnCartItemClickListener) {
        mOnCartItemClickListener = onCartItemClickListener
    }

    @Click(R.id.cart_item_quantity_add, R.id.cart_item_quantity_remove, R.id.cart_item_delete)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.cart_item_quantity_add -> handleQuantity(true)
            R.id.cart_item_quantity_remove -> handleQuantity(false)
            R.id.cart_item_delete -> mOnCartItemClickListener!!.onRemoveItem(CartItemDelete(Integer.valueOf(mCartProduct!!.key)))
        }
    }

    private fun handleQuantity(increase: Boolean) {
        if (!increase && mQuantity - 1 > 0) {
            mQuantity -= 1
            mQuantityCount!!.text = mQuantity.toString()
            mOnCartItemClickListener!!.onUpdateItemQuantity(mCartProduct!!.key, mQuantity)
        } else if (increase) {
            mQuantity += 1
            mQuantityCount!!.text = mQuantity.toString()
            mOnCartItemClickListener!!.onUpdateItemQuantity(mCartProduct!!.key, mQuantity)
        }
    }
}
