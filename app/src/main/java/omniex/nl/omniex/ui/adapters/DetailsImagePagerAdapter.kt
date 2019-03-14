package omniex.nl.omniex.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class DetailsImagePagerAdapter(fm: FragmentManager, private var mImageUrls: List<String>?) : FragmentStatePagerAdapter(fm) {

    fun setImageUrls(imageUrls: List<String>) {
        mImageUrls = imageUrls
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return DetailsImageFragment_.builder().mImageUrl(mImageUrls!![position]).build()
    }

    override fun getCount(): Int {
        return mImageUrls!!.size
    }
}
