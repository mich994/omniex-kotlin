package omniex.nl.omniex.ui.app.about

import android.webkit.WebView
import android.webkit.WebViewClient
import omniex.nl.omniex.R
import omniex.nl.omniex.ui.base.BaseFragment
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_about)
open class AboutFragment : BaseFragment<AboutView, AboutPresenter>(), AboutView {

    @ViewById(R.id.about_web_view)
    lateinit var mAboutWebView: WebView

    @AfterViews
    internal fun initWebView() {
        mAboutWebView!!.webViewClient = WebViewClient()
        val webSettings = mAboutWebView!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = true
        mAboutWebView!!.loadUrl("http://omniex.nl/index.php?route=information/information&information_id=4")
    }
}
