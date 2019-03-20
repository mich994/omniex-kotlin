package omniex.nl.omniex.ui.views.menu

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.data.model.MenuItem
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById

@EViewGroup(R.layout.view_menu_item)
open class MenuView(context: Context) : LinearLayout(context) {

    @ViewById(R.id.title_menu_item)
    lateinit var mTitle: TextView

    private val mPosition: Int = 0

    init {
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams = lp
    }

    fun bind(menuItem: MenuItem): omniex.nl.omniex.ui.views.menu.MenuView {
        mTitle!!.setText(menuItem.title)
        return this
    }

    fun setSelectedBg(isSelected: Boolean): MenuView {
        if (isSelected)
            mTitle!!.background = context.getDrawable(R.drawable.background_menu_item_light)
        else
            mTitle!!.background = context.getDrawable(R.drawable.background_menu_item_dark)
        return this
    }
}
