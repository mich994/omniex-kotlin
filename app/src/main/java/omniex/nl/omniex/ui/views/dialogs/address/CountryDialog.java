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
import nl.omniex.omniexshopping.data.model.address.Country;
import nl.omniex.omniexshopping.ui.adapters.CountriesAdapter;

@EFragment(R.layout.fragment_countries_dialog)
public class CountryDialog extends DialogFragment implements CountriesAdapter.OnCountrySelectedListener {

    @ViewById(R.id.countries_dialog_rv)
    RecyclerView mCountriesRv;

    @Inject
    CountriesAdapter mCountriesAdapter;

    @FragmentArg
    ArrayList<Country> mCountries;

    private CountriesAdapter.OnCountrySelectedListener mOnCountrySelectedListener;

    @AfterInject
    protected void afterInject(){
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
    void initCountryList() {
        mCountriesAdapter.setOnCountrySelectedListener(this);
        mCountriesRv.setAdapter(mCountriesAdapter);
        mCountriesAdapter.setItems(mCountries);
    }

    public CountryDialog setOnCountrySelectedListener(CountriesAdapter.OnCountrySelectedListener onCountrySelectedListener) {
        mOnCountrySelectedListener = onCountrySelectedListener;
        return this;
    }

    @Override
    public void onCountrySelected(Country country) {
        mOnCountrySelectedListener.onCountrySelected(country);
        dismiss();
    }
}
