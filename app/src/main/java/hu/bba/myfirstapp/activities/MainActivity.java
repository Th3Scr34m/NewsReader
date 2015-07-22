package hu.bba.myfirstapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    List<News> news;

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

                //not necessary
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        };

        apiService.getNewsResponse(callback);

        ListView myListView = (ListView) findViewById(R.id.main_listView);

        adapter = new CustomLayoutAdapter();
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CharSequence selection = News.getDescription();
//                String selection = parent.getItemAtPosition(position).toString();
//                Toast.makeText(MainActivity.this, selection, Toast.LENGTH_LONG).show();
                if (position == 1) {

                    Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {

                    Intent myIntent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }

//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch( position )
//                {
//                    case 0:  Intent newActivity_1 = new Intent(this, MainActivity.class);
//                        startActivity(newActivity_1);
//                        break;
//                    case 1:  Intent newActivity_2 = new Intent(this, DetailsActivity.class);
//                        startActivity(newActivity_2);
//                        break;
//                }
//            }
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
