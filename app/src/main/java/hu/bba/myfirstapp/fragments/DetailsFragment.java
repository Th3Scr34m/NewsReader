package hu.bba.myfirstapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;

public class DetailsFragment extends Fragment {

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.image)
    ImageView image;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        // TODO - Make to work with descriptions and images as well
        description.setText("Fragment is working with ViewPager.");
//        // Description
//        description.setText(news.getDescription());
//
//        // Image
//        Picasso.with(getActivity())
//                .load(news.getImage().getUrl())
//                .placeholder(R.mipmap.placeholder)
//                .error(R.mipmap.placeholder_err)
//                .into(image);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
