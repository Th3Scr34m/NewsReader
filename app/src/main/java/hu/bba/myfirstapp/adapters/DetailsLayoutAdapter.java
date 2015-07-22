package hu.bba.myfirstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import hu.bba.myfirstapp.models.News;

public class DetailsLayoutAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private List<News> details;

    public DetailsLayoutAdapter() {
        super();
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
        ViewHolder viewholder;

        return null;
    }

    public static class ViewHolder {

    }
}
