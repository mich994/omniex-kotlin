package omniex.nl.omniex.ui.views.fragments

import android.support.v4.app.Fragment
import android.widget.ImageView

import com.bumptech.glide.Glide
import omniex.nl.omniex.R
import omniex.nl.omniex.utils.StringUtils

import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.FragmentArg
import org.androidannotations.annotations.ViewById


@EFragment(R.layout.fragment_details_image)
open class DetailsImageFragment : Fragment() {

    @ViewById(R.id.details_item_image)
    lateinit var mDetailsImage: ImageView

    @FragmentArg
    lateinit var mImageUrl: String

    @AfterViews
    internal fun setImage() {
        Glide.with(this).load(StringUtils.fixUrl(mImageUrl)).into(mDetailsImage!!)
    }
}
