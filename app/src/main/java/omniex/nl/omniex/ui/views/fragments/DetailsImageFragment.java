package omniex.nl.omniex.ui.views.fragments;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import nl.omniex.omniexshopping.R;
import nl.omniex.omniexshopping.utils.StringUtils;

@EFragment(R.layout.fragment_details_image)
public class DetailsImageFragment extends Fragment {

    @ViewById(R.id.details_item_image)
    ImageView mDetailsImage;

    @FragmentArg
    String mImageUrl;

    @AfterViews
    void setImage(){
        Glide.with(this).load(StringUtils.fixUrl(mImageUrl)).into(mDetailsImage);
    }
}
