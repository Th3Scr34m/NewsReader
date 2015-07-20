package hu.bba.myfirstapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobprofs.retrofit.converters.SimpleXmlConverter;

import java.util.ArrayList;
import java.util.List;

import hu.bba.myfirstapp.R;
import hu.bba.myfirstapp.models.News;
import hu.bba.myfirstapp.models.NewsResponse;
import hu.bba.myfirstapp.services.ApiServices;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    List<News> news;
    private ArrayAdapter<News> adapter;

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
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        };

        apiService.getNewsResponse(callback);

        ListView myListView = (ListView) findViewById(R.id.main_listView);

        // TODO - try this method
        // public void success(List<User> users, Response response) {
        //  this is called in main thread so it's ok to update views
        //  mUsersAdapter.clear();
        //  mUsersAdapter.addAll(users);
        // }

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, news);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
