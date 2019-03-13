package omniex.nl.omniex.ui.app.newsletter;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.repository.NewsletterRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class NewsletterPresenter extends BasePresenter<NewsletterView> {

    private NewsletterRepository mNewsletterRepository;

    @Inject
    NewsletterPresenter(NewsletterRepository newsletterRepository) {
        mNewsletterRepository = newsletterRepository;
    }

    void subscribe() {
        ifViewAttached(view -> view.startLoading());
        addDisposable(mNewsletterRepository
                .subscribeNewsletter()
                .subscribe(voidResponse -> {
                    if (voidResponse.code() == 200)
                        ifViewAttached(view -> {
                            view.OnSubscriptionChange(true);
                            view.stopLoading();
                        });
                }, Throwable::printStackTrace));
    }

    void unsubscribe() {
        ifViewAttached(view -> view.startLoading());
        addDisposable(mNewsletterRepository
                .unsubscribeNewsletter()
                .subscribe(voidResponse -> {
                    if (voidResponse.code() == 200)
                        ifViewAttached(view -> {
                            view.OnSubscriptionChange(false);
                            view.stopLoading();
                        });
                }, Throwable::printStackTrace));
    }
}
