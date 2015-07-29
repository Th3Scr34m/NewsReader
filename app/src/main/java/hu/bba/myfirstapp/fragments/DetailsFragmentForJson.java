package hu.bba.myfirstapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.models.ContentImage;

public class DetailsFragmentForJson extends Fragment {

    private static final String ARG_NEWS = "newsParam";
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.image)
    ImageView image;

    private ContentImage contentImage;

    public DetailsFragmentForJson() {
    }

    public static DetailsFragmentForJson newInstance(ContentImage contentImage) {
        DetailsFragmentForJson fragment = new DetailsFragmentForJson();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, contentImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contentImage = (ContentImage) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        description.setText(contentImage.getFormattedContent());
        Picasso.with(getActivity())
                .load(contentImage.getFormattedUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_err)
                .into(image);

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
