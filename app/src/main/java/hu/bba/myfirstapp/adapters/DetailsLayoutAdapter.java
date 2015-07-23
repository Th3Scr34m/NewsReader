package hu.bba.myfirstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.models.News;

public class DetailsLayoutAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private List<News> details;

    public DetailsLayoutAdapter() {
        super();
    }

    public void initList(List<News> details) {
        this.details = details;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (details == null)
            return 0;
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        if (details == null)
            return null;
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHold viewhold;

        News details = (News) getItem(position);

        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            vi = inflater.inflate(R.layout.cutsom_layout, parent, false);

            viewhold = new ViewHold();
            viewhold.description = (TextView) vi.findViewById(R.id.description);
        } else
            viewhold = (ViewHold) vi.getTag();

        viewhold.description.setText(details.getDescription());
        return vi;
    }

    public static class ViewHold {
        public TextView description;
    }
}
