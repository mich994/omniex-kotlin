package omniex.nl.omniex.ui.views.toolbar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import omniex.nl.omniex.R

import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.ViewById


@EBean
class CustomToolbar {

    @ViewById(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @ViewById(R.id.toolbar_icon_start)
    lateinit var mIconStart: ImageView

    @ViewById(R.id.toolbar_icon_center)
    lateinit var mIconCenter: ImageView

    @ViewById(R.id.toolbar_icon_end)
    lateinit var mIconEnd: ImageView

    @ViewById(R.id.toolbar_text_center)
    lateinit var mTextCenter: TextView

    private var mIconStartClickListener: IconStartClickListener? = null
    private var mIconEndClickListner: IconEndClickListner? = null

    fun setBackground(color: Int): CustomToolbar {
        mToolbar!!.setBackgroundColor(color)
        return this
    }

    fun setIconStart(resId: Int): CustomToolbar {
        mIconStart!!.visibility = View.VISIBLE
        mIconStart!!.setImageResource(resId)
        return this
    }

    fun setIconEnd(resId: Int): CustomToolbar {
        mIconEnd!!.visibility = View.VISIBLE
        mIconEnd!!.setImageResource(resId)
        return this
    }

    fun setTextCenter(text: String): CustomToolbar {
        mTextCenter!!.text = text
        return this
    }

    fun setIconStarClickListener(iconStarClickListener: IconStartClickListener): CustomToolbar {
        mIconStartClickListener = iconStarClickListener
        return this
    }

    fun setIconEndClickListener(iconEndClickListener: IconEndClickListner): CustomToolbar {
        mIconEndClickListner = iconEndClickListener
        return this
    }

    @Click(R.id.toolbar_icon_start, R.id.toolbar_icon_end)
    internal fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_icon_start -> mIconStartClickListener!!.onIconStartClick()
            R.id.toolbar_icon_end -> mIconEndClickListner!!.onIconEndClick()
        }
    }

    interface IconStartClickListener {
        fun onIconStartClick()
    }

    interface IconEndClickListner {
        fun onIconEndClick()
    }
}
