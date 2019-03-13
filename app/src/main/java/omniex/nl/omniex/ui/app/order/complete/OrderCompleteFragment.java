package omniex.nl.omniex.ui.app.order.complete;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.ui.base.BaseFragment;

@EFragment(R.layout.fragment_order_complete)
public class OrderCompleteFragment extends BaseFragment<OrderCompleteView, OrderCompletePresenter> {

    @Click(R.id.order_complete_btn)
    void onBackBtnClick(){
        getActivity().finish();
    }
}
