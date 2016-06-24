package bbabics.hu.newsreader.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import bbabics.hu.newsreader.R;
import bbabics.hu.newsreader.models.Content;
import butterknife.Unbinder;

public class DetailsFragment extends Fragment {

    private static final String ARG_NEWS = "newsParam";
    @BindView(R.id.fragment_details_image)
    ImageView image;
    @BindView(R.id.fragment_details_date)
    TextView date;
    @BindView(R.id.fragment_details_description)
    TextView description;
    @BindView(R.id.fragment_details_caption)
    TextView caption;
    private Content content;

    Unbinder unbinder;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(Content content) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = (Content) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);
        unbinder = ButterKnife.bind(this, view);

        description.setText(Html.fromHtml(content.getFormattedContent()));
        date.setText(content.getFormattedDate());
        caption.setText(content.getFormattedCaption());
        Picasso.with(getActivity())
                .load(content.getFormattedUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_err)
                .into(image);

        return view;
    }

    @Override
    public void onDestroyView() {
        if(unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
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
