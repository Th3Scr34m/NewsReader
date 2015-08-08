package hu.bba.myfirstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.models.Content;

public class CustomLayoutAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private List<Content> newsList;

    public CustomLayoutAdapter() {
        super();
    }

    public static int getSize(List<Content> newsList) {
        return newsList.size();
    }

    public void initList(List<Content> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (newsList == null)
            return 0;
        return newsList.size();
    }

    @Override
    public Content getItem(int position) {
        if (newsList == null)
            return null;
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        Content content = getItem(position);

        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            vi = inflater.inflate(R.layout.cutsom_layout, parent, false);

            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.title);
            holder.link = (TextView) vi.findViewById(R.id.link);
            holder.desc = (TextView) vi.findViewById(R.id.desc);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (newsList.size() <= 0) {
            holder.title.setText("No Data");
        } else {

            holder.title.setText(content.getFormattedTitle());
            holder.link.setText(content.getVisibleUrl());
            holder.desc.setText(content.getFormattedContent());

            Picasso.with(parent.getContext())
                    .load(content.getThumbnailUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder_err)
                    .into(holder.image);
        }
        return vi;
    }

    public static class ViewHolder {
        protected TextView title;
        protected TextView link;
        protected TextView desc;
        protected ImageView image;
    }
}
