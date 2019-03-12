package omniex.nl.omniex.ui.base.menu

import android.content.Context
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import omniex.nl.omniex.data.model.MenuItem
import java.util.*

abstract class BaseMenuAdapter<Model:MenuItem, Row : View>  protected constructor(menuOptions: List<Model>) : BaseAdapter() {

    private var mMenuOptions: List<Model> = ArrayList()
    private val mSelected = 0

    private var mBooleanArray: SparseBooleanArray? = null

    init {
        resetMenuOptions(menuOptions)
    }

    fun setItems(menuOptions: List<Model>) {
        resetMenuOptions(menuOptions)
        notifyDataSetChanged()
    }

    fun setSelected(position: Int) {
        for (i in 0 until mBooleanArray!!.size()) {
            val key = mBooleanArray!!.keyAt(i)
            mBooleanArray!!.put(key, key == position)
        }
        notifyDataSetChanged()
    }

    fun isSelected(position: Int): Boolean {
        for (i in 0 until mBooleanArray!!.size()) {
            if (mBooleanArray!!.get(position))
                return true
        }
        return false
    }

    private fun resetMenuOptions(menuOptions: List<Model>) {
        mMenuOptions = menuOptions
        mBooleanArray = SparseBooleanArray()
        for (i in 0 until count)
            mBooleanArray!!.put(i, false)
    }

    override fun getCount(): Int {
        return mMenuOptions.size
    }

    override fun getItem(position: Int): Model {
        return mMenuOptions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val menuOption = mMenuOptions[position]
        val menuOptionView: Row
        if (convertView == null) {
            menuOptionView = createRowView(menuOption, parent.context, position)
        } else {
            menuOptionView = convertView as Row
        }
        return menuOptionView
    }

    protected abstract fun createRowView(model: Model, context: Context, position: Int): Row

    abstract fun <Model> getName(context: Context, model: Model): String

}
