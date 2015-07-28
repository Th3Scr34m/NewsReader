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
import hu.bba.myfirstapp.models.News;

public class DetailsFragment extends Fragment {

    private static final String ARG_NEWS = "newsParam";
    @Bind(R.id.description)
    TextView description;
    @Bind(R.id.image)
    ImageView image;
    private News news;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(News news) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (News) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        description.setText(news.getDescription());
        Picasso.with(getActivity())
                .load(news.getImage().getUrl())
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
