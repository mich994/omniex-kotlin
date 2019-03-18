package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.ui.adapters.ProductDetailsImageAdapter
import omniex.nl.omniex.utils.StringUtils

import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_details_image_item)
class ProductDetailsImageItemView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.details_item_image)
    lateinit var mDetailsImage: ImageView

    private var mImageUrl: String? = null
    private var mDetailsImageClickListener: ProductDetailsImageAdapter.OnDetailsImageClickListener? = null
    private var mPosition: Int = 0

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(imageUrl: String?, position: Int): ProductDetailsImageItemView {
        mImageUrl = StringUtils.fixUrl(imageUrl)
        mPosition = position
        Glide.with(this).load(mImageUrl).into(mDetailsImage!!)
        return this
    }

    fun setDetailsImageClickListener(detailsImageClickListener: ProductDetailsImageAdapter.OnDetailsImageClickListener) {
        mDetailsImageClickListener = detailsImageClickListener
    }

    @Click(R.id.details_item_image)
    internal fun onImageClick() {
        mDetailsImageClickListener!!.onImageClick(mPosition)
    }
}
