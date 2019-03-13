package omniex.nl.omniex.ui.app.main;

import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;

import nl.omniex.omniexshopping.data.model.cart.CartItemDelete;
import nl.omniex.omniexshopping.data.model.cart.CartQuantitySetter;
import nl.omniex.omniexshopping.data.repository.CartRepository;
import nl.omniex.omniexshopping.data.repository.OrderRepository;
import nl.omniex.omniexshopping.data.repository.ProfileRepository;
import nl.omniex.omniexshopping.ui.base.BasePresenter;
import nl.omniex.omniexshopping.ui.base.BaseView;
import nl.omniex.omniexshopping.utils.SharedPrefUtils;
import timber.log.Timber;

public class MainMenuPresenter extends BasePresenter<MainMenuView> {

    private ProfileRepository mProfileRepository;
    private CartRepository mCartRepository;
    private OrderRepository mOrderRepository;

    @Inject
    MainMenuPresenter(ProfileRepository profileRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        mProfileRepository = profileRepository;
        mCartRepository = cartRepository;
        mOrderRepository = orderRepository;
    }

    void logout() {
        addDisposable(mProfileRepository
                .logout()
                .subscribe(
                        voidResponse -> {
                            if (voidResponse.code() == 200) {
                                SharedPrefUtils.setUserLogged(false);
                                ifViewAttached(MainMenuView::onLogoutSuccess);
                            }
                        }, Throwable::printStackTrace));
    }

    void getCart() {
        ifViewAttached(BaseView::startLoading);
        addDisposable(mCartRepository
                .getCart()
                .subscribe(
                        cartResponse ->
                        {
                            ifViewAttached(view -> {
                                view.onCartFetched(cartResponse.body().getCart());
                                view.stopLoading();
                            });
                        },
                        error -> {
                            error.printStackTrace();
                            if(error instanceof JsonSyntaxException)
                                ifViewAttached(view -> view.onCartEmpty());
                            Timber.e(error);
                            ifViewAttached(BaseView::stopLoading);
                        }));
    }

    void updateCartQuantity(CartQuantitySetter cartQuantitySetter) {
        addDisposable(mCartRepository
                .updateCartItemQuantity(cartQuantitySetter)
                .subscribe(voidResponse -> {
                    if(voidResponse.code()==200)
                        ifViewAttached(MainMenuView::onCartItemQuantityUpdated);
                }, Throwable::printStackTrace));
    }

    void deleteCartItem(CartItemDelete cartItemDelete) {
        addDisposable(mCartRepository
                .deleteCartItem(cartItemDelete)
                .subscribe(voidResponse -> {
                    if(voidResponse.code()==200)
                        ifViewAttached(MainMenuView::onCartItemQuantityUpdated);
                }, Throwable::printStackTrace));
    }
}
