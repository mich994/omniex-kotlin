package omniex.nl.omniex.ui.app.profile.order;

import java.util.List;

import nl.omniex.omniexshopping.data.model.order.OrderStatus;
import nl.omniex.omniexshopping.ui.base.BaseView;

public interface ProfileOrderView extends BaseView {

    void onOrderStatusesFetched(List<OrderStatus> orderStatusList);
}
