package omniex.nl.omniex.ui.app.order.payment.method

import omniex.nl.omniex.data.model.payment.PaymentMethodSetter
import omniex.nl.omniex.data.repository.PaymentRespository
import omniex.nl.omniex.ui.base.BasePresenter
import javax.inject.Inject

class OrderPaymentMethodsPresenter @Inject
internal constructor(private val mPaymentRespository: PaymentRespository) : BasePresenter<OrderPaymentMethodsView>() {

    internal fun getPaymentMethods() {
        addDisposable(mPaymentRespository
                .paymentMethods()
                .subscribe({ voidResponse -> }, { error -> error.printStackTrace() }))
    }

    internal fun setPaymentMethods(paymentMethod: PaymentMethodSetter) {
        addDisposable(mPaymentRespository
                .setPaymentMethod(paymentMethod)
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { view -> view.onPaymentMethodSet() }
                }, { error -> error.printStackTrace() }))
    }
}
