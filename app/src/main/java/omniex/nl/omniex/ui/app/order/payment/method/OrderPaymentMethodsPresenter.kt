package omniex.nl.omniex.ui.app.order.payment.method

import javax.inject.Inject

import nl.omniex.omniexshopping.data.model.payment.PaymentMethodSetter
import nl.omniex.omniexshopping.data.repository.PaymentRespository
import nl.omniex.omniexshopping.ui.base.BasePresenter

class OrderPaymentMethodsPresenter @Inject
internal constructor(private val mPaymentRespository: PaymentRespository) : BasePresenter<OrderPaymentMethodsView>() {

    internal fun getPaymentMethods() {
        addDisposable(mPaymentRespository
                .getPaymentMethods()
                .subscribe({ voidResponse -> }, ???({ printStackTrace() })))
    }

    internal fun setPaymentMethods(paymentMethod: PaymentMethodSetter) {
        addDisposable(mPaymentRespository
                .setPaymentMethod(paymentMethod)
                .subscribe({ voidResponse ->
                    if (voidResponse.code() === 200)
                        ifViewAttached { view -> view.onPaymentMethodSet() }
                }, ???({ printStackTrace() })))
    }
}
