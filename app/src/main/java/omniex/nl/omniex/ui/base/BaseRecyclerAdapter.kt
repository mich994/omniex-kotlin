package omniex.nl.omniex.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import java.util.*

abstract class BaseRecyclerAdapter<T, V : View> : RecyclerView.Adapter<BaseRecyclerAdapter.ViewWrapper<V>>() {

    private val mItems = ArrayList<T>()
    var itemClickListener: ItemClickListener<T>? = null
    var onBottomReachedListener: OnBottomReachedListener? = null

    val items: List<T>
        get() = mItems


    fun setItems(list: List<T>){
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> {
        return ViewWrapper(onCreateItemView(parent, viewType))
    }

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItem(position: Int): T? {
        return if (position > -1 && position < mItems.size) {
            mItems[position]
        } else null
    }

    fun addItems(items: List<T>) {
        mItems.addAll(items)
        notifyItemRangeChanged(mItems.size - items.size, mItems.size)
    }

    fun addItem(item: T) {
        mItems.add(item)
        notifyItemInserted(mItems.size)
    }

    fun removeItem(position: Int) {
        mItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeItem(item: T) {
        val removedIndex = mItems.indexOf(item)
        mItems.remove(item)
        notifyDataSetChanged()
    }

    fun getPosition(item: T): Int {
        return mItems.indexOf(item)
    }

    fun setClickListener(itemClick: ItemClickListener<T>){
        itemClickListener = itemClick
    }

    class ViewWrapper<V : View>(val view: V) : RecyclerView.ViewHolder(view)

    interface ItemClickListener<Model> {
        fun onItemClick(model: Model?)
    }

    interface OnBottomReachedListener {
        fun onBottomReached()
    }

}
