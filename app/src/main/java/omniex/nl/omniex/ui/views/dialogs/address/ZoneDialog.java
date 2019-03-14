package omniex.nl.omniex.ui.views.dialogs.address;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.data.model.address.Province;
import nl.omniex.omniexshopping.ui.adapters.ZonesAdapter;

@EFragment(R.layout.fragment_zones_dialog)
public class ZoneDialog extends DialogFragment implements ZonesAdapter.OnZoneSelectedClickListener {

    @ViewById(R.id.zones_dialog_rv)
    RecyclerView mZonesRv;

    @Inject
    ZonesAdapter mZonesAdapter;

    @FragmentArg
    ArrayList<Province> mProvinces;

    private ZonesAdapter.OnZoneSelectedClickListener mOnZoneSelectedClickListener;

    @AfterInject
    protected void afterInject() {
        try {
            AndroidSupportInjection.inject(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @AfterViews
    void initZoneList() {
        mZonesAdapter.setOnZoneSelectedClickListener(this);
        mZonesRv.setAdapter(mZonesAdapter);
        mZonesAdapter.setItems(mProvinces);
    }

    public ZoneDialog setOnZoneSelectedClickListener(ZonesAdapter.OnZoneSelectedClickListener onZoneSelectedClickListener) {
        mOnZoneSelectedClickListener = onZoneSelectedClickListener;
        return this;
    }

    @Override
    public void onZoneSelected(Province province) {
        mOnZoneSelectedClickListener.onZoneSelected(province);
        dismiss();
    }
}