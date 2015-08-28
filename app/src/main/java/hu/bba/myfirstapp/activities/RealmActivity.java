package hu.bba.myfirstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.adapters.RealmAdapter;
import hu.bba.myfirstapp.models.AddObject;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private static RealmAdapter adapter;
    private static Context context;
    private static int resId;

    @Bind(R.id.realm_toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realm_layout);

        ButterKnife.bind(this);

        toolbar.setTitle("Realm data");
        setSupportActionBar(toolbar);

        ListView myListView = (ListView) findViewById(R.id.main_listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(myListView);

        adapter = new RealmAdapter(context, resId, readFromFile(), true);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent toDetailsIntent = new Intent(RealmActivity.this, DetailsActivity.class);
            toDetailsIntent.putExtra("Position", position);
            startActivity(toDetailsIntent);
        });
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        Intent toAddIntent = new Intent(RealmActivity.this, RealmActivity.class);
        startActivity(toAddIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public RealmResults<AddObject> readFromFile() {

        Realm realm = Realm.getDefaultInstance();

        RealmQuery<AddObject> query = realm.where(AddObject.class);
        RealmResults<AddObject> results = query.findAll();
        results.sort("realmDate");

        return results;
    }
}
