package omniex.nl.omniex.ui.app.order;

import nl.omniex.omniexshopping.ui.base.BaseView;

public interface OrderView extends BaseView {
    void onOrderOverviewSuccess();
    void onOrderOverviewFailed();
}
