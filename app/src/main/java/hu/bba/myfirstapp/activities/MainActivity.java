package hu.bba.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobprofs.retrofit.converters.SimpleXmlConverter;

import java.util.ArrayList;
import java.util.List;

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

    ArrayList<News> news;
    Toolbar ActionBarToolbar;

    private CustomLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SimpleXML - Retrofit
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://t.aff.hu/")
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

            }
        };

        apiService.getNewsResponse(callback);

        ActionBarToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(ActionBarToolbar);
        getSupportActionBar().setTitle("Home");

        ListView myListView = (ListView) findViewById(R.id.main_listView);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
