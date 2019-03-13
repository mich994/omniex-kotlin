package omniex.nl.omniex.ui.app.order.payment.method;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.payment.PaymentMethodSetter;
import nl.omniex.omniexshopping.data.repository.PaymentRespository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;

public class OrderPaymentMethodsPresenter extends BasePresenter<OrderPaymentMethodsView> {

    private PaymentRespository mPaymentRespository;

    @Inject
    OrderPaymentMethodsPresenter(PaymentRespository paymentRespository){
        mPaymentRespository =paymentRespository;
    }

    void getPaymentMethods(){
        addDisposable(mPaymentRespository
        .getPaymentMethods()
        .subscribe(voidResponse -> {}, Throwable::printStackTrace));
    }

    void setPaymentMethods(PaymentMethodSetter paymentMethod){
        addDisposable(mPaymentRespository
                .setPaymentMethod(paymentMethod)
                .subscribe(voidResponse -> {
                    if(voidResponse.code()==200)
                        ifViewAttached(view -> view.onPaymentMethodSet());
                }, Throwable::printStackTrace));
    }
}
