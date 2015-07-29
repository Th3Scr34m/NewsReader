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
import hu.bba.myfirstapp.models.ContentImage;

public class CustomLayoutAdapterForJson extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private List<ContentImage> newsList;

    public CustomLayoutAdapterForJson() {
        super();
    }

    public static int getSize(List<ContentImage> newsList) {
        return newsList.size();
    }

    public void initList(List<ContentImage> newsList) {
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
    public ContentImage getItem(int position) {
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

        ContentImage contentImage = getItem(position);

        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            vi = inflater.inflate(R.layout.cutsom_layout, parent, false);

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.date = (TextView) vi.findViewById(R.id.date);
            holder.address = (TextView) vi.findViewById(R.id.address);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (newsList.size() <= 0) {
            holder.text.setText("No Data");
        } else {

            holder.text.setText(contentImage.getFormattedTitle());
            holder.date.setText(contentImage.getVisibleUrl());
            holder.address.setText(contentImage.getFormattedContent());

            Picasso.with(parent.getContext())
                    .load(contentImage.getThumbnailUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder_err)
                    .into(holder.image);
        }
        return vi;
    }

    public static class ViewHolder {
        protected TextView text;
        protected TextView date;
        protected TextView address;
        protected ImageView image;
    }
}
