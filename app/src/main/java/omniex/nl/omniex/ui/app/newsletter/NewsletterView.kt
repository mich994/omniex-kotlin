package omniex.nl.omniex.ui.app.newsletter

import omniex.nl.omniex.ui.base.BaseView


interface NewsletterView : BaseView {

    fun OnSubscriptionChange(isSubscribed: Boolean)
}
