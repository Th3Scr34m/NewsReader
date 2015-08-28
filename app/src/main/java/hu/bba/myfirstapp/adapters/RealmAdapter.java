package hu.bba.myfirstapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import hu.bba.myfirstapp.models.AddObject;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class RealmAdapter extends RealmBaseAdapter<AddObject> implements ListAdapter {

    public RealmAdapter(Context context, int resId, RealmResults<AddObject> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public RealmResults<AddObject> getRealmResults() {
        return realmResults;
    }

    public void setResults(RealmResults<AddObject> realmResults) {
        this.realmResults = realmResults;
        notifyDataSetChanged();
    }
}
