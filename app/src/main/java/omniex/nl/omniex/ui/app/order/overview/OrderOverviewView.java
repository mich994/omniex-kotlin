package omniex.nl.omniex.ui.app.order.overview;

import nl.omniex.omniexshopping.ui.base.BaseView;

public interface OrderOverviewView extends BaseView {
    void onOrderConfirmed();
    void onOrderConfirmedFailed();
}
