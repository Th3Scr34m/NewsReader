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
import hu.bba.myfirstapp.models.News;

public class CustomLayoutAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private List<News> newsList;

    public CustomLayoutAdapter() {
        super();
    }

    public static int getSize(List<News> newsList) {
        return newsList.size();
    }

    public void initList(List<News> newsList) {
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
    public News getItem(int position) {
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

        News news = getItem(position);

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

            holder.text.setText(news.getName());
            holder.date.setText(news.getPubDate());
            holder.address.setText(news.getAddress().toString());
            // holder.image.
            Picasso.with(parent.getContext())
                    .load(news.getImage().getUrl())
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder_err)
                    .into(holder.image);
        }
        return vi;
    }

    public static class ViewHolder {
        public TextView text;
        public TextView date;
        public TextView address;
        public ImageView image;
    }
}
