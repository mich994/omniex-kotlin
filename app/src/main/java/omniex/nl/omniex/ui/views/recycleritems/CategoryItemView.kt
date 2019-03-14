package omniex.nl.omniex.ui.views.recycleritems

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.products.Category
import omniex.nl.omniex.ui.base.BaseRecyclerAdapter
import omniex.nl.omniex.utils.StringUtils
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_category_item)
class CategoryItemView(context: Context) : FrameLayout(context) {

    @ViewById(R.id.category_item_image)
    lateinit var mCategoryImage: ImageView

    @ViewById(R.id.category_item_name)
    lateinit var mCategoryName: TextView

    private var mItemClickListener: BaseRecyclerAdapter.ItemClickListener<Category>? = null
    private var mCategory: Category? = null

    init {
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
    }

    fun bind(category: Category): CategoryItemView {
        mCategory = category
        mCategoryName!!.setText(mCategory!!.name)
        Glide.with(this).load(StringUtils.fixUrl(mCategory!!.imageUrl)).into(mCategoryImage!!)
        return this
    }

    fun setItemClickListener(itemClickListener: BaseRecyclerAdapter.ItemClickListener<Category>) {
        mItemClickListener = itemClickListener
    }

    @Click(R.id.category_item_name)
    internal fun onClick() {
        mItemClickListener!!.onItemClick(mCategory)
    }
}
