package omniex.nl.omniex.ui.app.newsletter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.ui.base.BaseFragment;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;

@EFragment(R.layout.fragment_newsletter)
public class NewsletterFragment extends BaseFragment<NewsletterView, NewsletterPresenter> implements NewsletterView {

    @ViewById(R.id.newsletter_status_iv)
    ImageView mStatusImage;

    @ViewById(R.id.newsletter_status_tv)
    TextView mStatusText;

    @ViewById(R.id.newsletter_btn)
    Button mSubscriptionButton;

    private boolean mIsSubscribed;

    @AfterViews
    void initStatus(){
        mIsSubscribed = SharedPrefUtils.isNewsletterSubscribed();
        if(mIsSubscribed){
            mStatusImage.setImageResource(R.drawable.subscribed_status_icon);
            mStatusText.setText(getString(R.string.newsletter_subscribed_info));
            mSubscriptionButton.setText("unsubscribe");
        }else {
            mStatusImage.setImageResource(R.drawable.unsubscribed_status_icon);
            mStatusText.setText(getString(R.string.newsletter_unsubscribed_info));
            mSubscriptionButton.setText("subscribe");
        }
    }

    @Click(R.id.newsletter_btn)
    void onSubBtnClick(){
        if(mIsSubscribed)
            getPresenter().unsubscribe();
        else
            getPresenter().subscribe();
    }

    @Override
    public void OnSubscriptionChange(boolean isSubscribed) {
        SharedPrefUtils.setNewsletterStatus(isSubscribed);
        initStatus();
    }

}
