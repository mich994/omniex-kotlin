package omniex.nl.omniex.ui.app.newsletter

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import omniex.nl.omniex.R
import omniex.nl.omniex.ui.base.BaseFragment
import omniex.nl.omniex.utils.SharedPrefUtils
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById


@EFragment(R.layout.fragment_newsletter)
open class NewsletterFragment : BaseFragment<NewsletterView, NewsletterPresenter>(), NewsletterView {

    @ViewById(R.id.newsletter_status_iv)
    lateinit var mStatusImage: ImageView

    @ViewById(R.id.newsletter_status_tv)
    lateinit var mStatusText: TextView

    @ViewById(R.id.newsletter_btn)
    lateinit var mSubscriptionButton: Button

    private var mIsSubscribed: Boolean = false

    @AfterViews
    internal fun initStatus() {
        mIsSubscribed = SharedPrefUtils.isNewsletterSubscribed()
        if (mIsSubscribed) {
            mStatusImage!!.setImageResource(R.drawable.subscribed_status_icon)
            mStatusText!!.setText(getString(R.string.newsletter_subscribed_info))
            mSubscriptionButton!!.text = "unsubscribe"
        } else {
            mStatusImage!!.setImageResource(R.drawable.unsubscribed_status_icon)
            mStatusText!!.setText(getString(R.string.newsletter_unsubscribed_info))
            mSubscriptionButton!!.text = "subscribe"
        }
    }

    @Click(R.id.newsletter_btn)
    internal fun onSubBtnClick() {
        if (mIsSubscribed)
            getPresenter().unsubscribe()
        else
            getPresenter().subscribe()
    }

    override fun OnSubscriptionChange(isSubscribed: Boolean) {
        SharedPrefUtils.setNewsletterStatus(isSubscribed)
        initStatus()
    }

}
