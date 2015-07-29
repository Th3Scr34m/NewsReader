package hu.bba.myfirstapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.mobprofs.retrofit.converters.SimpleXmlConverter;

import java.util.ArrayList;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.adapters.CustomLayoutAdapter;
import hu.bba.myfirstapp.models.News;
import hu.bba.myfirstapp.models.NewsResponse;
import hu.bba.myfirstapp.services.ApiServices;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<News> news;
    private Toolbar ActionBarToolbar;
    private CustomLayoutAdapter adapter;
    private CharSequence text = "The newsfeed is not available";
    private int duration = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context activityContext = this;

        // SimpleXML - Retrofit
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://aff-test.azurewebsites.net/")
                .setConverter(new SimpleXmlConverter())
                .build();

        ApiServices apiService = restAdapter.create(ApiServices.class);

        news = new ArrayList<>();
        Callback<NewsResponse> callback = new Callback<NewsResponse>() {
            @Override
            public void success(NewsResponse newsResponse, Response response) {
                news = newsResponse.getNewsList();
                adapter.initList(news);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast toast = Toast.makeText(activityContext, text, duration);
                toast.show();
            }
        };

        apiService.getNewsResponse(callback);

        ActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(ActionBarToolbar);
        getSupportActionBar().setTitle("Main Page");

        ListView myListView = (ListView) findViewById(R.id.main_listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(myListView);

        adapter = new CustomLayoutAdapter();
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                myIntent.putExtra("News", news);
                myIntent.putExtra("Position", position);
                startActivity(myIntent);
            }
        });
    }
}
