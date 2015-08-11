package hu.bba.myfirstapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.ButterKnife;
import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.adapters.CustomLayoutAdapter;
import hu.bba.myfirstapp.fragments.AlertDialogFragment;
import hu.bba.myfirstapp.models.Content;
import hu.bba.myfirstapp.models.ContentDataResponse;
import hu.bba.myfirstapp.services.ApiServices;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Content> content;
    private static CustomLayoutAdapter adapter;
    private static int duration = Toast.LENGTH_LONG;
    private static String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;

    final Context activityContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (isOnline()) {
            ButterKnife.bind(this);

            Gson gson = new GsonBuilder()
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(String.format(getString(R.string.api_endpoint)))
                    .setConverter(new GsonConverter(gson))
                    .build();

            ApiServices apiService = restAdapter.create(ApiServices.class);

            content = new ArrayList<>();

            Callback<ContentDataResponse> callback = new Callback<ContentDataResponse>() {
                @Override
                public void success(ContentDataResponse contentImageDataResponse, Response response) {
                    content = contentImageDataResponse.getResultList().getImageList();
                    adapter.initList(content);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast toast = Toast.makeText(activityContext, R.string.main_feed_unavailable, duration);
                    toast.show();
                }
            };

            apiService.getContentImageDataResponse(callback);

            toolbar.setTitle("Main Page");
            setSupportActionBar(toolbar);

            ListView myListView = (ListView) findViewById(R.id.main_listView);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.attachToListView(myListView);

            adapter = new CustomLayoutAdapter();
            myListView.setAdapter(adapter);

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent toDetailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    toDetailsIntent.putExtra("Content", content);
                    toDetailsIntent.putExtra("Position", position);
                    startActivity(toDetailsIntent);
                }
            });
        }
        else {
            AlertDialogFragment af = AlertDialogFragment.newInstance(R.string.internet_dialog, R.string.internet_dialog_text);

            af.setCallback(retry -> {
                if(retry) {
                    recreate();
                }
                else {
                    finish();
                    System.exit(0);
                }
            });
            af.show(this.getSupportFragmentManager(), TAG);
        }
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        Intent toAddIntent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(toAddIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(activityContext);
        dialog.setContentView(R.layout.internet_dialog_layout);
        dialog.setTitle(R.string.internet_dialog);
        dialog.show();
    }
}
