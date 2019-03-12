package omniex.nl.omniex.ui.base.menu

import android.content.Context
import omniex.nl.omniex.data.model.MenuItem
import omniex.nl.omniex.ui.views.menu.MenuView
import omniex.nl.omniex.ui.views.menu.MenuView_


class MenuAdapter(menuItems: List<MenuItem>) : BaseMenuAdapter<MenuItem, MenuView>(menuItems) {

    override fun createRowView(menuItem: MenuItem, context: Context, position: Int): omniex.nl.omniex.ui.views.menu.MenuView {
        return MenuView_.build(context).bind(menuItem)
    }

    override fun <Model> getName(context: Context, model: Model): String = model.toString()
}
