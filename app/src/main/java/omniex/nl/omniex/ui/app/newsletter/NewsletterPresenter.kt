package omniex.nl.omniex.ui.app.newsletter

import omniex.nl.omniex.data.repository.NewsletterRepository
import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject


class NewsletterPresenter @Inject
internal constructor(private val mNewsletterRepository: NewsletterRepository) : BasePresenter<NewsletterView>() {

    internal fun subscribe() {
        ifViewAttached { view -> view.startLoading() }
        addDisposable(mNewsletterRepository
                .subscribeNewsletter()
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { view ->
                            view.OnSubscriptionChange(true)
                            view.stopLoading()
                        }
                }, { it.printStackTrace() }))
    }

    internal fun unsubscribe() {
        ifViewAttached { view -> view.startLoading() }
        addDisposable(mNewsletterRepository
                .unsubscribeNewsletter()
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { view ->
                            view.OnSubscriptionChange(false)
                            view.stopLoading()
                        }
                }, { it.printStackTrace() }))
    }
}
